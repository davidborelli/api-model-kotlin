package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.ServicoInterrupcao
import br.com.gimb.api.model.vo.ServicoInterrupcaoVO
import br.com.gimb.api.services.ServicoInterrupcaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/servico-interrupcao")
@RestController
class ServicoInterrupcaoController {

    @Autowired
    lateinit var servicoInterrupcaoService: ServicoInterrupcaoService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<ServicoInterrupcao>>{
        val listaServicoInterrupcao = servicoInterrupcaoService.buscarTodos()
        return ConstruirResposta.lista(listaServicoInterrupcao, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<ServicoInterrupcao> {
        val servicoInterrupcao = servicoInterrupcaoService.buscarPorId(id)
        return ConstruirResposta.objeto(servicoInterrupcao, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody servicoInterrupcaoVO: ServicoInterrupcaoVO): ResponseEntity<ServicoInterrupcao> {
        val servicoInterrupcao = servicoInterrupcaoService.salvar(servicoInterrupcaoVO)
        return ConstruirResposta.objeto(servicoInterrupcao, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody servicoInterrupcaoVO: ServicoInterrupcaoVO, @PathVariable id: Long): ResponseEntity<ServicoInterrupcao> {
        val servicoInterrupcao = servicoInterrupcaoService.atualizar(servicoInterrupcaoVO, id)
        return ConstruirResposta.objeto(servicoInterrupcao, HttpMethod.POST)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        servicoInterrupcaoService.deletar(id)
    }

}
