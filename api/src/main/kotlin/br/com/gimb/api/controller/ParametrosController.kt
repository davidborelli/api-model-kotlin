package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Parametro
import br.com.gimb.api.repository.ParametrosRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/parametros")
class ParametrosController {

    @Autowired
    lateinit var parametrosRepository: ParametrosRepository;

    @GetMapping
    fun buscarParametros(): ResponseEntity<Parametro> {
        val parametros = parametrosRepository.findAll();
        return ConstruirResposta.objeto(parametros[0], HttpMethod.GET)
    }
}
