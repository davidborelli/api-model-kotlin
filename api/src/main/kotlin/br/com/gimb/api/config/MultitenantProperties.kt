package br.com.gimb.api.config

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("multitenant.app")
class MultitenantProperties {

    lateinit var lclUsername: String
    lateinit var lclPassword: String
    lateinit var lclDataSources: List<DataSourceProperties>

    lateinit var prdUsername: String
    lateinit var prdPassword: String
    lateinit var prdDataSources: List<DataSourceProperties>

    lateinit var pocUsername: String
    lateinit var pocPassword: String
    lateinit var pocDataSources: List<DataSourceProperties>



}
