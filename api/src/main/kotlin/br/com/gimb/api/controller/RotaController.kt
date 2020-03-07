package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Rota
import br.com.gimb.api.model.vo.RotaVO
import br.com.gimb.api.services.PerfilService
import br.com.gimb.api.services.RotaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/rotas")
@RestController
class RotaController {

    @Autowired
    lateinit var rotaService: RotaService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<Rota>>{
        val listaRota = rotaService.buscarTodos()
        return ConstruirResposta.lista(listaRota, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Rota> {
        val rota = rotaService.buscarPorId(id)
        return ConstruirResposta.objeto(rota, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody rotaVO: RotaVO): ResponseEntity<Rota> {
        val rota = rotaService.salvar(rotaVO)
        return ConstruirResposta.objeto(rota, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody rotaVO: RotaVO, @PathVariable id: Long): ResponseEntity<Rota> {
        val rota = rotaService.atualizar(rotaVO, id)
        return ConstruirResposta.objeto(rota, HttpMethod.POST)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        rotaService.deletar(id)
    }

}
