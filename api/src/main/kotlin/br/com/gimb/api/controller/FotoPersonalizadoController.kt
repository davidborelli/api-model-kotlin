package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.FotoPersonalizado
import br.com.gimb.api.model.vo.FotoPersonalizadoVO
import br.com.gimb.api.services.FotoPersonalizadoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/fotos-personalizados")
class FotoPersonalizadoController {

    @Autowired
    lateinit var fotoPersonalizadoService: FotoPersonalizadoService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<FotoPersonalizado>> {
        val listaFotoPersonalizado = fotoPersonalizadoService.buscarTodos()
        return ConstruirResposta.lista(listaFotoPersonalizado, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<FotoPersonalizado> {
        val fotoPersonalizado = fotoPersonalizadoService.buscarPorId(id)
        return ConstruirResposta.objeto(fotoPersonalizado, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody fotoPersonalizadoVO: FotoPersonalizadoVO): ResponseEntity<FotoPersonalizado> {
        val fotoPersonalizado = fotoPersonalizadoService.salvar(fotoPersonalizadoVO)
        return ConstruirResposta.objeto(fotoPersonalizado, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody fotoPersonalizadoVO: FotoPersonalizadoVO, @PathVariable id: Long): ResponseEntity<FotoPersonalizado> {
        val fotoPersonalizado = fotoPersonalizadoService.atualizar(fotoPersonalizadoVO, id)
        return ConstruirResposta.objeto(fotoPersonalizado, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long){
        fotoPersonalizadoService.delete(id)
    }
}
