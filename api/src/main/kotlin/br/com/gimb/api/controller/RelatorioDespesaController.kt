package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.RelatorioDespesa
import br.com.gimb.api.model.vo.RelatorioDespesaVO
import br.com.gimb.api.services.RelatorioDespesaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/relatorio-despesa")
@RestController
class RelatorioDespesaController {

    @Autowired
    lateinit var relatorioDespesaService: RelatorioDespesaService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<RelatorioDespesa>> {
        val listaRelatorioDespesa = relatorioDespesaService.buscarTodos()
        return ConstruirResposta.lista(listaRelatorioDespesa, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorIr(@PathVariable id: Long): ResponseEntity<RelatorioDespesa> {
        val relatorioDespesa = relatorioDespesaService.buscarPorId(id)
        return ConstruirResposta.objeto(relatorioDespesa, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody relatorioDespesaVO: RelatorioDespesaVO): ResponseEntity<RelatorioDespesa> {
        val relatorioDespesa = relatorioDespesaService.salvar(relatorioDespesaVO)
        return ConstruirResposta.objeto(relatorioDespesa, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody relatorioDespesaVO: RelatorioDespesaVO, @PathVariable id: Long): ResponseEntity<RelatorioDespesa> {
        val relatorioDespesa = relatorioDespesaService.atualizar(relatorioDespesaVO, id)
        return ConstruirResposta.objeto(relatorioDespesa, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long){
        relatorioDespesaService.deletar(id)
    }
}
