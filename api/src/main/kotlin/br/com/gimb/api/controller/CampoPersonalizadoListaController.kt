package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.CampoPersonalizadoLista
import br.com.gimb.api.model.vo.CampoPersonalizadoListaVO
import br.com.gimb.api.services.CampoPersonalizadoListaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/campos-personalizados-lista")
class CampoPersonalizadoListaController {

    @Autowired
    lateinit var campoPersonalizadoListaService: CampoPersonalizadoListaService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<CampoPersonalizadoLista>> {
        val listaCampoPersonalizadoLista = campoPersonalizadoListaService.buscarTodos()
        return ConstruirResposta.lista(listaCampoPersonalizadoLista, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorIr(@PathVariable id: Long): ResponseEntity<CampoPersonalizadoLista>{
        val campoPersonalizadoLista = campoPersonalizadoListaService.buscarPorId(id)
        return ConstruirResposta.objeto(campoPersonalizadoLista, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody campoPersonalizadoListaVo: CampoPersonalizadoListaVO): ResponseEntity<CampoPersonalizadoLista> {
        val campoPersonalizadoLista = campoPersonalizadoListaService.salvar(campoPersonalizadoListaVo)
        return ConstruirResposta.objeto(campoPersonalizadoLista, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody campoPersonalizadoListaVo: CampoPersonalizadoListaVO, @PathVariable id: Long): ResponseEntity<CampoPersonalizadoLista> {
        val campoPersonalizadoLista = campoPersonalizadoListaService.atualizar(campoPersonalizadoListaVo, id)
        return ConstruirResposta.objeto(campoPersonalizadoLista, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        campoPersonalizadoListaService.deletar(id)
    }
}
