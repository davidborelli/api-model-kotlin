package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.AgendaItens
import br.com.gimb.api.model.AgendaItensVO
import br.com.gimb.api.services.AgendaItensService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/agenda-itens")
class AgendaItensController {

    @Autowired
    lateinit var agendaItensService: AgendaItensService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<AgendaItens>>{
        val listaAgenda = agendaItensService.buscarTodos()
        return ConstruirResposta.lista(listaAgenda, HttpMethod.GET)
    }

    @GetMapping("/{guid}")
    fun buscarPorId(@PathVariable guid: String): ResponseEntity<MutableList<AgendaItens>>{
        val agendaItens = agendaItensService.burcasTodosPorGuid(guid)
        return ConstruirResposta.lista(agendaItens, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody agendaItensVO: AgendaItensVO): ResponseEntity<AgendaItens> {
        val agendaItens = agendaItensService.salvar(agendaItensVO)
        return ConstruirResposta.objeto(agendaItens, HttpMethod.POST)
    }

    @PutMapping("/{guid}")
    fun atualizar(@RequestBody agendaItensVO: AgendaItensVO, @PathVariable guid: String): ResponseEntity<AgendaItens>{
        val agendaItens = agendaItensService.atualizar(agendaItensVO, guid)
        return ConstruirResposta.objeto(agendaItens, HttpMethod.PUT)
    }

    @DeleteMapping("/{guid}")
    fun deletar(@PathVariable guid: String) {
        return agendaItensService.deletarPorGuid(guid)
    }
}
