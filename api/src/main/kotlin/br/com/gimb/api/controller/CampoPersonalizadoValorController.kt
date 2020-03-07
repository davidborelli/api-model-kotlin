package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.CampoPersonalizadoValor
import br.com.gimb.api.model.vo.CampoPersonalizadoValorVO
import br.com.gimb.api.services.CampoPersonalizadoValorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/campos-personalizados-valor")
class CampoPersonalizadoValorController {

    @Autowired
    lateinit var campoPersonalizadoValorService: CampoPersonalizadoValorService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<CampoPersonalizadoValor>> {
        val listaCampoPersonalizadoValor = campoPersonalizadoValorService.buscarTodos()
        return ConstruirResposta.lista(listaCampoPersonalizadoValor, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorIr(@PathVariable id: Long): ResponseEntity<CampoPersonalizadoValor>{
        val campoPersonalizadoValor = campoPersonalizadoValorService.buscarPorId(id)
        return ConstruirResposta.objeto(campoPersonalizadoValor, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody campoPersonalizadoValorVo: CampoPersonalizadoValorVO): ResponseEntity<CampoPersonalizadoValor> {
        val campoPersonalizadoValor = campoPersonalizadoValorService.salvar(campoPersonalizadoValorVo)
        return ConstruirResposta.objeto(campoPersonalizadoValor, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody campoPersonalizadoValorVo: CampoPersonalizadoValorVO, @PathVariable id: Long): ResponseEntity<CampoPersonalizadoValor> {
        val campoPersonalizadoValor = campoPersonalizadoValorService.atualizar(campoPersonalizadoValorVo, id)
        return ConstruirResposta.objeto(campoPersonalizadoValor, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        campoPersonalizadoValorService.deletar(id)
    }
}
