package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Observacao
import br.com.gimb.api.model.vo.ObservacaoVO
import br.com.gimb.api.services.ObservacaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/observacoes")
class ObservacaoController {

    @Autowired
    lateinit var observacaoService: ObservacaoService

    @GetMapping
    fun buscarTodos(@RequestParam("ativo", required = false) ativo: Boolean?): ResponseEntity<MutableList<Observacao>> {
        val listaObservacao = observacaoService.buscarTodos(ativo)
        return ConstruirResposta.lista(listaObservacao, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Observacao> {
        val observacao = observacaoService.buscarPorId(id)
        return ConstruirResposta.objeto(observacao, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody observacaoVO: ObservacaoVO): ResponseEntity<Observacao> {
        val observacao = observacaoService.salvar(observacaoVO)
        return ConstruirResposta.objeto(observacao, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody observacaoVO: ObservacaoVO, @PathVariable id: Long): ResponseEntity<Observacao> {
        val observacao = observacaoService.atualizar(observacaoVO, id)
        return ConstruirResposta.objeto(observacao, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        observacaoService.deletar(id)
    }
}
