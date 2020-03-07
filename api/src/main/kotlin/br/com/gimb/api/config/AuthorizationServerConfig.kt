package br.com.gimb.api.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig: AuthorizationServerConfigurerAdapter() {

    @Autowired
    lateinit var authenticationManager: WebSecurityConfiguration

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients?.inMemory()
                ?.withClient("angular")
                ?.secret("{noop}@ngul@r0")
                ?.scopes("read", "write")
                ?.authorizedGrantTypes("password", "refresh_token")
                ?.accessTokenValiditySeconds(3600)
                ?.refreshTokenValiditySeconds(3600)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints
                ?.tokenStore(tokenStore())
                ?.accessTokenConverter(accessTokenConverter())
                ?.reuseRefreshTokens(true) //Caso nao seja setado, refresh token tera o prazo somente de 24h, conforme configurado
                ?.authenticationManager(authenticationManager.authenticationManagerBean())
    }


    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter {
        var jwtAccessTokenConverter = JwtAccessTokenConverter()
        jwtAccessTokenConverter.setSigningKey("1a522ddf24c6f1fa2ab2b430a2b732c1") //chave secreta Jwt G1mB14p!K@Mu!t4rt3C
        return jwtAccessTokenConverter
    }

    @Bean
    fun tokenStore(): TokenStore {
        return JwtTokenStore(accessTokenConverter())
    }
}
