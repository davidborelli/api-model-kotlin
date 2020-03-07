package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.DespesaItem
import br.com.gimb.api.model.vo.DespesaItemVO
import br.com.gimb.api.services.DespesaItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/despesas-items")
class DespesaItemController {

    @Autowired
    lateinit var despesaItemService: DespesaItemService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<DespesaItem>> {
        val listaDespesaItem = despesaItemService.buscarTodos()
        return ConstruirResposta.lista(listaDespesaItem, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<DespesaItem> {
        val despesaItem = despesaItemService.buscarPorId(id)
        return ConstruirResposta.objeto(despesaItem, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody despesaItemVO: DespesaItemVO): ResponseEntity<DespesaItem> {
        val despesaItem = despesaItemService.salvar(despesaItemVO)
        return ConstruirResposta.objeto(despesaItem, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody despesaItemVO: DespesaItemVO, @PathVariable id: Long): ResponseEntity<DespesaItem> {
        val despesaItem = despesaItemService.atualizar(despesaItemVO, id)
        return ConstruirResposta.objeto(despesaItem, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun excluir(@PathVariable id: Long) {
        despesaItemService.delete(id)
    }
}
