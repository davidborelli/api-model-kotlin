package br.com.gimb.api

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [ApiApplication::class])
@WebAppConfiguration
class ApiApplicationTests {

	var mvc: MockMvc? = null
	val server = "http://localhost:8080"

	@Autowired
	lateinit var webApplicationContext: WebApplicationContext

	fun setup(){
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
	}

	fun <T> mapToJson(objeto: T): String {
		val objectMapper = ObjectMapper()
		return objectMapper.writeValueAsString(objeto)
	}

	fun <T> mapFromJson(json: String?, clazz: Class<T>): T {
		val objectMapper = ObjectMapper()
		return objectMapper.readValue(json, clazz)
	}
}
