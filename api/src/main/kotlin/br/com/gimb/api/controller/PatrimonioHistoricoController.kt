package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.PatrimonioHistorico
import br.com.gimb.api.model.vo.PatrimonioHistoricoVO
import br.com.gimb.api.services.PatrimonioHistoricoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/patrimonioHistorico-historicos")
@RestController
class PatrimonioHistoricoController {

    @Autowired
    lateinit var patrimonioHistoricoService: PatrimonioHistoricoService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<PatrimonioHistorico>> {
        var listaPatrimonioHistorico = patrimonioHistoricoService.buscarTodos()
        return ConstruirResposta.lista(listaPatrimonioHistorico, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<PatrimonioHistorico>{
        var patrimonioHistorico = patrimonioHistoricoService.buscarPorId(id)
        return ConstruirResposta.objeto(patrimonioHistorico, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody patrimonioHistoricoVO: PatrimonioHistoricoVO): ResponseEntity<PatrimonioHistorico> {
        var patrimonioHistorico = patrimonioHistoricoService.salvar(patrimonioHistoricoVO)
        return ConstruirResposta.objeto(patrimonioHistorico, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody patrimonioHistoricoVO: PatrimonioHistoricoVO, @PathVariable id: Long): ResponseEntity<PatrimonioHistorico> {
        var patrimonioHistorico = patrimonioHistoricoService.atualizar(patrimonioHistoricoVO, id)
        return ConstruirResposta.objeto(patrimonioHistorico, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        patrimonioHistoricoService.deletar(id)
    }
}
