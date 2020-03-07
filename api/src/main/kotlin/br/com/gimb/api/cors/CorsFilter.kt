package br.com.gimb.api.cors

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class CorsFilter:  Filter{

    val originPermitida = "http://localhost:4200" //TODO: Configurar para diferentes ambientes

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {

        val req = request as HttpServletRequest
        val resp = response as HttpServletResponse

        response.setHeader("Access-Control-Allow-Origin", originPermitida)
        response.setHeader("Access-Control-Allow-Credentials", "true") //Precisa dessa notacao para o cookie ser enviado

        if ("OPTIONS" == request.method && originPermitida == request.getHeader("Origin")){
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS")
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept")
            response.setHeader("Access-Control-Max-Age", "3600")

            response.status = HttpServletResponse.SC_OK
        } else {
            chain?.doFilter(req, resp)
        }
    }
}
