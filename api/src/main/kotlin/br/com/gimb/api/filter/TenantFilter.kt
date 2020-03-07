package br.com.gimb.api.filter

import br.com.gimb.api.config.TenantContext
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

class TenantFilter : GenericFilterBean() {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        var serverName = request?.serverName ?: "localhost"
        if (serverName.contains("192") || serverName.contains("127"))
            serverName = "localhost"

        TenantContext.setCurrentTenant(serverName)
        chain?.doFilter(request, response)
    }

}
