package br.com.gimb.api.config

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import javax.sql.DataSource

class MultitenantDataSource : AbstractRoutingDataSource() {

    override fun determineCurrentLookupKey(): Any? {
        return TenantContext.getCurrentTenant()
    }

}