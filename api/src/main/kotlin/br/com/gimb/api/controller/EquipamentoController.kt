package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Equipamento
import br.com.gimb.api.model.vo.EquipamentoVO
import br.com.gimb.api.repository_http.EquipamentoHttp
import br.com.gimb.api.services.EquipamentoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/equipamentos")
@RestController
class EquipamentoController {

    @Autowired
    lateinit var equipamentoService: EquipamentoService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<Equipamento>>{
        val listaEquipamento: MutableList<Equipamento>

        if (true) {
            listaEquipamento = EquipamentoHttp().pegarTodos()
        } else {
            listaEquipamento = equipamentoService.buscarTodos();
        }

        return ConstruirResposta.lista(listaEquipamento, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Equipamento> {
        val equipamento: Equipamento?

        if (true) {
            equipamento = EquipamentoHttp().buscarPorId(id)
        }
        else {
            equipamento = equipamentoService.buscarPorId(id)
        }

        return ConstruirResposta.objeto(equipamento, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody equipamentoVO: EquipamentoVO): ResponseEntity<Equipamento> {
        val equipamento = equipamentoService.salvar(equipamentoVO)
        return ConstruirResposta.objeto(equipamento, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody equipamentoVO: EquipamentoVO, @PathVariable id: Long): ResponseEntity<Equipamento> {
        val equipamento = equipamentoService.atualizar(equipamentoVO, id)
        return ConstruirResposta.objeto(equipamento, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        equipamentoService.deletar(id)
    }
}
