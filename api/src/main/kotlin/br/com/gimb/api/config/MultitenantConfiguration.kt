package br.com.gimb.api.config

import br.com.gimb.api.enumarated.EnvironmentEnum
import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


@Configuration
class MultitenantConfiguration {

    @Autowired
    private lateinit var properties: DataSourceProperties

    @Autowired
    private lateinit var multiProperties: MultitenantProperties

    var LIST = "LIST"
    var USER = "USER"
    var PASS = "PASS"

    /**
     * Defines the data source for the application
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    fun dataSource(): DataSource {
        // Create the final multi-tenant source.
        // It needs a default database to connect to.
        // Make sure that the default database is actually an empty tenant database.
        // Don't use that for a regular tenant if you want things to be safe!
        val dataSource = MultitenantDataSource()
        dataSource.setDefaultTargetDataSource(defaultDataSource())
        dataSource.setTargetDataSources(resolvedDataSources())
        // Call this to finalize the initialization of the data source.
        dataSource.afterPropertiesSet()

        return dataSource
    }

    private fun environmentProperties(): Map<String, Any> {
        var environment = EnvironmentEnum.LOCAL
        try {
            if (System.getenv("GIMB_ENV") != null)
                environment = EnvironmentEnum.valueOf(System.getenv("GIMB_ENV"))
        } catch (e: Exception) {
            environment = EnvironmentEnum.LOCAL
        }

        var username = ""
        var password = ""
        var dataSourceList: List<DataSourceProperties>? = null
        when (environment) {
            EnvironmentEnum.LOCAL -> {
                dataSourceList = multiProperties.lclDataSources
                username = multiProperties.lclUsername
                password = multiProperties.lclPassword
            }
            EnvironmentEnum.POC -> {
                dataSourceList = multiProperties.pocDataSources
                username = multiProperties.pocUsername
                password = multiProperties.pocPassword
            }
            EnvironmentEnum.PRODUCAO -> {
                dataSourceList = multiProperties.prdDataSources
                username = multiProperties.prdUsername
                password = multiProperties.prdPassword
            }
        }

        val properties = HashMap<String, Any>()
        properties[LIST] = dataSourceList
        properties[USER] = username
        properties[PASS] = password

        return properties
    }

    /**
     * Creates the default data source for the application
     * @return
     */
    private fun defaultDataSource(): DataSource {
        val envProp = environmentProperties()
        val username = envProp[USER].toString()
        val password = envProp[PASS].toString()

        val dataSourceBuilder = DataSourceBuilder.create(this.javaClass.classLoader)
                .driverClassName(properties.driverClassName)
                .url(properties.url)
                .username(username)
                .password(password)

        if (properties.type != null)
            dataSourceBuilder.type(properties.type)

        return dataSourceBuilder.build()
    }

    private fun resolvedDataSources(): MutableMap<Any, Any> {
        val envProp = environmentProperties()
        val dataSourceList = envProp[LIST] as List<DataSourceProperties>
        val username = envProp[USER].toString()
        val password = envProp[PASS].toString()
        val resolvedDataSources = HashMap<Any, Any>()


        for (dsProperties in dataSourceList) {
            val dataSourceBuilder = DataSourceBuilder.create(this.javaClass.classLoader)
            try {
                dataSourceBuilder.driverClassName(properties.driverClassName)
                        .url(dsProperties.url)
                        .username(username)
                        .password(password)

                if (properties.type != null)
                    dataSourceBuilder.type(properties.type)

                resolvedDataSources[dsProperties.name] = executeMigration(dataSourceBuilder.build())
            } catch (e: Exception) {
                e.printStackTrace()
                throw Exception(e)
            }

        }

        return resolvedDataSources
    }

    private fun executeMigration(ds: DataSource): DataSource {
        try {
            var isEmpty = true
            val flyway = Flyway.configure().dataSource(ds).locations("db/migration").load()
            val current = flyway.info().current()

            val tables = ds.connection.metaData.getTables(null, null, "user", null)
            if (tables.next())
                isEmpty = false

            if (current == null && !isEmpty)
                flyway.baseline() // needed because flyway doesn't baseline empty schemas

            flyway.migrate()

            ds.connection.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ds
    }

}