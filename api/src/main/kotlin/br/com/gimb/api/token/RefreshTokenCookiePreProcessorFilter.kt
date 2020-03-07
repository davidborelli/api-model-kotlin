package br.com.gimb.api.token

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
import org.apache.catalina.util.ParameterMap

@Component
@Order(Ordered.HIGHEST_PRECEDENCE) //Precisa analisar essa requisicao antes de todos
class RefreshTokenCookiePreProcessorFilter: Filter {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        var req  = request as HttpServletRequest

        //Verifico se existe Cookie
        if ("/oauth/token".equals(req.requestURI, ignoreCase = true)
                && "refresh_token" == req.getParameter("grant_type")
                && req.cookies != null) {
            for (cookie in req.cookies) {
                if (cookie.name == "refreshToken"){
                    val refreshToken = cookie.value
                    req = MyServletRequestWrapper(req, refreshToken)
                }
            }
        }

        chain?.doFilter(req, response)
    }

    internal class MyServletRequestWrapper(request: HttpServletRequest, private val refreshToken: String) : HttpServletRequestWrapper(request) {

        override fun getParameterMap(): Map<String, Array<String>> {
            val map = ParameterMap(request.parameterMap)
            map["refresh_token"] = arrayOf(refreshToken)
            map.isLocked = true
            return map
        }

    }
}
