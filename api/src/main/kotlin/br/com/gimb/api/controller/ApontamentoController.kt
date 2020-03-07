package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Apontamento
import br.com.gimb.api.model.vo.ApontamentoVO
import br.com.gimb.api.services.ApontamentoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/apontamentos")
class ApontamentoController {

    @Autowired
    lateinit var apontamentoService: ApontamentoService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<Apontamento>> {
        val listaApontamentos = apontamentoService.buscarTodos()
        return ConstruirResposta.lista(listaApontamentos, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Apontamento> {
        val apontamento = apontamentoService.buscarPorId(id)
        return ConstruirResposta.objeto(apontamento, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody apontamentoVo: ApontamentoVO): ResponseEntity<Apontamento>{
        val apontamento = apontamentoService.salvar(apontamentoVo)
        return ConstruirResposta.objeto(apontamento, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody apontamentoVo: ApontamentoVO, @PathVariable id: Long): ResponseEntity<Apontamento>{
        val apontamento = apontamentoService.atualizar(apontamentoVo, id)
        return ConstruirResposta.objeto(apontamento, HttpMethod.PUT)
    }

    @DeleteMapping
    fun deletar(@PathVariable id: Long){
        apontamentoService.deletarPorId(id)
    }

}
