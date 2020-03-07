package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.CampoDescricoes
import br.com.gimb.api.model.vo.CampoDescricoesVO
import br.com.gimb.api.services.CampoDescricoesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/campos-descricoes")
class CampoDescricoesController {

    @Autowired
    lateinit var campoDescricoesService: CampoDescricoesService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<CampoDescricoes>> {
        val listaCampoDescricoes = campoDescricoesService.buscarTodos()
        return ConstruirResposta.lista(listaCampoDescricoes, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorIr(@PathVariable id: Long): ResponseEntity<CampoDescricoes>{
        val campoDescricoes = campoDescricoesService.buscarPorId(id)
        return ConstruirResposta.objeto(campoDescricoes, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody campoDescricoesVo: CampoDescricoesVO): ResponseEntity<CampoDescricoes> {
        val campoDescricoes = campoDescricoesService.salvar(campoDescricoesVo)
        return ConstruirResposta.objeto(campoDescricoes, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody campoDescricoesVo: CampoDescricoesVO, @PathVariable id: Long): ResponseEntity<CampoDescricoes> {
        val campoDescricoes = campoDescricoesService.atualizar(campoDescricoesVo, id)
        return ConstruirResposta.objeto(campoDescricoes, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        campoDescricoesService.deletar(id)
    }
}
