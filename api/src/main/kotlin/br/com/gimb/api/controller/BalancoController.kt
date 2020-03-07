package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Balanco
import br.com.gimb.api.model.vo.BalancoVO
import br.com.gimb.api.services.BalancoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/balancos")
class BalancoController {

    @Autowired
    lateinit var balancoService: BalancoService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<Balanco>> {
        val listaBalanco = balancoService.buscarTodos()
        return ConstruirResposta.lista(listaBalanco, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Balanco> {
        val balanco = balancoService.buscarPorId(id)
        return ConstruirResposta.objeto(balanco, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody balancoVo: BalancoVO): ResponseEntity<Balanco> {
        val balanco = balancoService.salvar(balancoVo)
        return ConstruirResposta.objeto(balanco, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody balancoVo: BalancoVO, @PathVariable id: Long): ResponseEntity<Balanco> {
        val balanco = balancoService.atualizar(balancoVo, id)
        return ConstruirResposta.objeto(balanco, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
       balancoService.deletarPorId(id)
    }
}
