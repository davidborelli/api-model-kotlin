package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.TipoEquipamento
import br.com.gimb.api.model.vo.TipoEquipamentoVO
import br.com.gimb.api.services.TipoEquipamentoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/tipo-equipamento")
@RestController
class TipoEquipamentoController {

    @Autowired
    lateinit var tipoEquipamentoService: TipoEquipamentoService

    @GetMapping
    fun buscarTodos(@RequestParam("ativo", required = false) ativo: Boolean): ResponseEntity<MutableList<TipoEquipamento>>{
        val listaTipoEquipamento = tipoEquipamentoService.buscarTodos(ativo)
        return ConstruirResposta.lista(listaTipoEquipamento, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<TipoEquipamento> {
        val tipoEquipamento = tipoEquipamentoService.buscarPorId(id)
        return ConstruirResposta.objeto(tipoEquipamento, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody tipoEquipamentoVO: TipoEquipamentoVO): ResponseEntity<TipoEquipamento> {
        val tipoEquipamento = tipoEquipamentoService.salvar(tipoEquipamentoVO)
        return ConstruirResposta.objeto(tipoEquipamento, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody tipoEquipamentoVO: TipoEquipamentoVO, @PathVariable id: Long): ResponseEntity<TipoEquipamento> {
        val tipoEquipamento = tipoEquipamentoService.atualizar(tipoEquipamentoVO, id)
        return ConstruirResposta.objeto(tipoEquipamento, HttpMethod.POST)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        tipoEquipamentoService.deletar(id)
    }

}
