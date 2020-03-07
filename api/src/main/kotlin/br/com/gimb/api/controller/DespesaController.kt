package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Despesa
import br.com.gimb.api.model.vo.DespesaVO
import br.com.gimb.api.services.DespesaItemService
import br.com.gimb.api.services.DespesaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/despesas")
class DespesaController {

    @Autowired
    lateinit var despesaService: DespesaService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<Despesa>> {
        val listaDespesa = despesaService.buscarTodos()
        return ConstruirResposta.lista(listaDespesa, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Despesa> {
        val despesa = despesaService.buscarPorId(id)
        return ConstruirResposta.objeto(despesa, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody despesaVO: DespesaVO): ResponseEntity<Despesa> {
        val despesa = despesaService.salvar(despesaVO)
        return ConstruirResposta.objeto(despesa, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody despesaVO: DespesaVO, @PathVariable id: Long): ResponseEntity<Despesa> {
        val despesa = despesaService.atualizar(despesaVO, id)
        return ConstruirResposta.objeto(despesa, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun excluir(@PathVariable id: Long) {
        despesaService.deletar(id)
    }
}
