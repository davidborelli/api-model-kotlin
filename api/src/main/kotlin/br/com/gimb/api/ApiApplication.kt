package br.com.gimb.api

import br.com.gimb.api.filter.TenantFilter
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.context.annotation.Bean
import javax.servlet.Filter

@SpringBootApplication
class ApiApplication : SpringBootServletInitializer() {

	@Bean
	fun adicionaFiltros() : FilterRegistrationBean<Filter> {
		val filtro = FilterRegistrationBean<Filter>()
		filtro.filter = TenantFilter()

		return filtro

	}

}

fun main(args: Array<String>) {
	SpringApplication.run(ApiApplication::class.java, *args)
}