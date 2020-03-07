package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Servico
import br.com.gimb.api.model.vo.ServicoVO
import br.com.gimb.api.services.ServicoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/servicos")
@RestController
class ServicoController {

    @Autowired
    lateinit var servicoService: ServicoService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<Servico>>{
        val listaServico = servicoService.buscarTodos()
        return ConstruirResposta.lista(listaServico, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Servico> {
        val servico = servicoService.buscarPorId(id)
        return ConstruirResposta.objeto(servico, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody servicoVO: ServicoVO): ResponseEntity<Servico> {
        val servico = servicoService.salvar(servicoVO)
        return ConstruirResposta.objeto(servico, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody servicoVO: ServicoVO, @PathVariable id: Long): ResponseEntity<Servico> {
        val servico = servicoService.atualizar(servicoVO, id)
        return ConstruirResposta.objeto(servico, HttpMethod.POST)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        servicoService.deletar(id)
    }

}
