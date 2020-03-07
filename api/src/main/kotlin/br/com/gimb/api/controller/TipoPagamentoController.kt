package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.TipoPagamento
import br.com.gimb.api.model.vo.TipoPagamentoVO
import br.com.gimb.api.services.TipoPagamentoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/tipo-pagamento")
@RestController
class TipoPagamentoController {

    @Autowired
    lateinit var tipoPagamentoService: TipoPagamentoService

    @GetMapping
    fun buscarTodos(@RequestParam("ativo", required = false) ativo: Boolean): ResponseEntity<MutableList<TipoPagamento>>{
        val listaTipoPagamento = tipoPagamentoService.buscarTodos(ativo)
        return ConstruirResposta.lista(listaTipoPagamento, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<TipoPagamento> {
        val tipoPagamento = tipoPagamentoService.buscarPorId(id)
        return ConstruirResposta.objeto(tipoPagamento, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody tipoPagamentoVO: TipoPagamentoVO): ResponseEntity<TipoPagamento> {
        val tipoPagamento = tipoPagamentoService.salvar(tipoPagamentoVO)
        return ConstruirResposta.objeto(tipoPagamento, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody tipoPagamentoVO: TipoPagamentoVO, @PathVariable id: Long): ResponseEntity<TipoPagamento> {
        val tipoPagamento = tipoPagamentoService.atualizar(tipoPagamentoVO, id)
        return ConstruirResposta.objeto(tipoPagamento, HttpMethod.POST)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        tipoPagamentoService.deletar(id)
    }

}
