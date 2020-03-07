package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.ApontamentoEvento
import br.com.gimb.api.model.vo.ApontamentoEventoVO
import br.com.gimb.api.services.ApontamentoEventoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/apontamentos-evento")
class ApontamentoEventoController {

    @Autowired
    lateinit var apontamentoEventoService: ApontamentoEventoService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<ApontamentoEvento>> {
        val listaApontamentosEvento = apontamentoEventoService.buscarTodos()
        return ConstruirResposta.lista(listaApontamentosEvento, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<ApontamentoEvento> {
        val apontamentoEvento = apontamentoEventoService.buscarPorId(id)
        return ConstruirResposta.objeto(apontamentoEvento, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody apontamentoEventoVo: ApontamentoEventoVO): ResponseEntity<ApontamentoEvento>{
        val apontamentoEvento = apontamentoEventoService.salvar(apontamentoEventoVo)
        return ConstruirResposta.objeto(apontamentoEvento, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody apontamentoEventoVo: ApontamentoEventoVO, @PathVariable id: Long): ResponseEntity<ApontamentoEvento>{
        val apontamentoEvento = apontamentoEventoService.atualizar(apontamentoEventoVo, id)
        return ConstruirResposta.objeto(apontamentoEvento, HttpMethod.PUT)
    }

    @DeleteMapping
    fun deletar(@PathVariable id: Long){
        apontamentoEventoService.deletarPorId(id)
    }

}
