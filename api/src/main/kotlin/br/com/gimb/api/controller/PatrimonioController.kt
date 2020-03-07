package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Patrimonio
import br.com.gimb.api.model.vo.PatrimonioVO
import br.com.gimb.api.services.PatrimonioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/patrimonios")
@RestController
class PatrimonioController {

    @Autowired
    lateinit var patrimonioService: PatrimonioService

    @GetMapping
    fun buscarTodos(@RequestParam("ativo", required = false) ativo: Boolean): ResponseEntity<MutableList<Patrimonio>> {
        var listaPatrimonio = patrimonioService.buscarTodos(ativo)
        return ConstruirResposta.lista(listaPatrimonio, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Patrimonio>{
        var patrimonio = patrimonioService.buscarPorId(id)
        return ConstruirResposta.objeto(patrimonio, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody patrimonioVO: PatrimonioVO): ResponseEntity<Patrimonio> {
        var patrimonio = patrimonioService.salvar(patrimonioVO)
        return ConstruirResposta.objeto(patrimonio, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody patrimonioVO: PatrimonioVO, @PathVariable id: Long): ResponseEntity<Patrimonio> {
        var patrimonio = patrimonioService.atualizar(patrimonioVO, id)
        return ConstruirResposta.objeto(patrimonio, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        patrimonioService.deletar(id)
    }
}
