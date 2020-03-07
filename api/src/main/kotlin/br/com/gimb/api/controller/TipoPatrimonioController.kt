package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.TipoPatrimonio
import br.com.gimb.api.model.vo.TipoPatrimonioVO
import br.com.gimb.api.services.TipoPatrimonioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/tipo-patrimonio")
@RestController
class TipoPatrimonioController {

    @Autowired
    lateinit var tipoPatrimonioService: TipoPatrimonioService

    @GetMapping
    fun buscarTodos(@RequestParam("ativo", required = false) ativo: Boolean): ResponseEntity<MutableList<TipoPatrimonio>>{
        val listaTipoPatrimonio = tipoPatrimonioService.buscarTodos(ativo)
        return ConstruirResposta.lista(listaTipoPatrimonio, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<TipoPatrimonio> {
        val tipoPatrimonio = tipoPatrimonioService.buscarPorId(id)
        return ConstruirResposta.objeto(tipoPatrimonio, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody tipoPatrimonioVO: TipoPatrimonioVO): ResponseEntity<TipoPatrimonio> {
        val tipoPatrimonio = tipoPatrimonioService.salvar(tipoPatrimonioVO)
        return ConstruirResposta.objeto(tipoPatrimonio, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody tipoPatrimonioVO: TipoPatrimonioVO, @PathVariable id: Long): ResponseEntity<TipoPatrimonio> {
        val tipoPatrimonio = tipoPatrimonioService.atualizar(tipoPatrimonioVO, id)
        return ConstruirResposta.objeto(tipoPatrimonio, HttpMethod.POST)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        tipoPatrimonioService.deletar(id)
    }

}
