package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Extrato
import br.com.gimb.api.model.vo.ExtratoVO
import br.com.gimb.api.services.ExtratoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/extratos")
@RestController
class ExtratoController {

    @Autowired
    lateinit var extratoService: ExtratoService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<Extrato>> {
        val listaExtrato = extratoService.buscarTodos()
        return ConstruirResposta.lista(listaExtrato, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Extrato> {
        val extrato = extratoService.buscarPorId(id)
        return ConstruirResposta.objeto(extrato, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody extratoVO: ExtratoVO): ResponseEntity<Extrato> {
        val extrato = extratoService.salvar(extratoVO)
        return ConstruirResposta.objeto(extrato, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody extratoVO: ExtratoVO, @PathVariable id: Long): ResponseEntity<Extrato> {
        val extrato = extratoService.atualizar(extratoVO, id)
        return ConstruirResposta.objeto(extrato, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        extratoService.deletar(id)
    }
}
