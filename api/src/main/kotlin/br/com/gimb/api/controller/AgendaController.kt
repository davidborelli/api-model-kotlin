package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Agenda
import br.com.gimb.api.model.AgendaVO
import br.com.gimb.api.repository.AgendaRepository
import br.com.gimb.api.repository.filter.AgendaFiltro
import br.com.gimb.api.services.AgendaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/agendas")
class AgendaController {

    @Autowired
    lateinit var agendaService: AgendaService

    @Autowired
    lateinit var agendaRepository: AgendaRepository

    @GetMapping
    fun filtrar(agendaFiltro: AgendaFiltro): ResponseEntity<MutableList<Agenda>>{
        val listaAgenda = agendaRepository.filtrar(agendaFiltro)
        return ConstruirResposta.lista(listaAgenda, HttpMethod.GET)
    }

//    @GetMapping
//    fun buscarTodos(): ResponseEntity<MutableList<Agenda>>{
//        val listaAgenda = agendaService.buscarTodos()
//        return ConstruirResposta.lista(listaAgenda, HttpMethod.GET)
//    }

    @GetMapping("/{guid}")
    fun buscarPorGuid(@PathVariable guid: String): ResponseEntity<Agenda>{
        val agenda = agendaService.buscarPorGuid(guid)
        return ConstruirResposta.objeto(agenda, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody agendaVO: AgendaVO): ResponseEntity<Agenda> {
        val agenda = agendaService.salvar(agendaVO)
        return ConstruirResposta.objeto(agenda, HttpMethod.POST)
    }

    @PutMapping("/{guid}")
    fun atualizar(@RequestBody agendaVO: AgendaVO, @PathVariable guid: String): ResponseEntity<Agenda>{
        val agenda = agendaService.atualizar(agendaVO, guid)
        return ConstruirResposta.objeto(agenda, HttpMethod.PUT)
    }

    @DeleteMapping("/{guid}")
    fun deletar(@PathVariable guid: String) {
        return agendaService.deletarPorGuid(guid)
    }
}
