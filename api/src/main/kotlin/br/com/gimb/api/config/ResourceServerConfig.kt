package br.com.gimb.api.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer

@Configuration
@EnableResourceServer
class ResourceServerConfig: ResourceServerConfigurerAdapter() {

    @Autowired
    fun configure(auth: AuthenticationManagerBuilder){
        auth?.inMemoryAuthentication()
                ?.withUser("admin")
                ?.password("{noop}admin")
                ?.roles("ADMIN")
    }

    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()
                    ?.antMatchers("/api/**")?.authenticated()
                    ?.and()
                ?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.and()
                ?.csrf()?.disable()
    }

    override fun configure(resources: ResourceServerSecurityConfigurer?) {
        resources?.stateless(true)
    }

    private fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }
}

