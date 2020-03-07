package br.com.gimb.api.token

import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class RefreshTokenPostProcessor: ResponseBodyAdvice<OAuth2AccessToken> { //Quando for um corpo de resposta de OAuth2AccessToken, irá passar por aqui  = Tipo do dado para ser interceptado quando estiver voltando

    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean {
    //Somente vai executar o método abaixo quando passar tbm por esse filtro
        return returnType.method?.name.equals("postAccessToken")
    }

    override fun beforeBodyWrite(body: OAuth2AccessToken?, returnType: MethodParameter, selectedContentType: MediaType, selectedConverterType: Class<out HttpMessageConverter<*>>, request: ServerHttpRequest, response: ServerHttpResponse): OAuth2AccessToken? {

        val req =  (request as ServletServerHttpRequest).servletRequest
        val resp = (response as ServletServerHttpResponse).servletResponse

        val token = body as DefaultOAuth2AccessToken

        val refreshToken = body?.refreshToken?.value
        adicionarRefreshTokenCookie(refreshToken, req, resp)

        removerRefreshTokenDoBody(token)

        return body
    }

    private fun removerRefreshTokenDoBody(token: DefaultOAuth2AccessToken) {
        token.refreshToken = null
    }

    private fun adicionarRefreshTokenCookie(refreshToken: String?, req: HttpServletRequest, resp: HttpServletResponse) {
        val refreshTokenCookie = Cookie("refreshToken", refreshToken)

        refreshTokenCookie.isHttpOnly
        refreshTokenCookie.secure = false //TODO: Mudar par true em producao
        refreshTokenCookie.path = "${req.contextPath}/oauth/token"
        refreshTokenCookie.maxAge = 2592000

        resp.addCookie(refreshTokenCookie)
    }
}
