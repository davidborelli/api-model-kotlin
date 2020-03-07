package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Almoxarifado
import br.com.gimb.api.model.vo.AlmoxarifadoVO
import br.com.gimb.api.services.AlmoxarifadoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/almoxarifados")
class AlmoxarifadoController {

    @Autowired
    lateinit var almoxarifadoService: AlmoxarifadoService

//    @GetMapping
//    fun buscarTodos(@RequestParam("ativo", required = false) ativo: Boolean?, pageable: Pageable): Page<Almoxarifado> {
//        val listaAlmoxarifado = almoxarifadoService.buscarTodos(ativo, pageable)
//        return PageImpl<Almoxarifado>(listaAlmoxarifado)
//    }

    @GetMapping
    fun buscarTodos(@RequestParam("ativo", required = false) ativo: Boolean?): ResponseEntity<MutableList<Almoxarifado>> {
        val listaAlmoxarifado = almoxarifadoService.buscarTodos(ativo)
        return ConstruirResposta.lista(listaAlmoxarifado, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Almoxarifado> {
        val almoxarifado = almoxarifadoService.buscarPorId(id)
        return ConstruirResposta.objeto(almoxarifado, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody almoxarifadoVo: AlmoxarifadoVO): ResponseEntity<Almoxarifado> {
        val almoxarifado = almoxarifadoService.salvar(almoxarifadoVo)
        return ConstruirResposta.objeto(almoxarifado, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody almoxarifadoVo: AlmoxarifadoVO, @PathVariable id: Long): ResponseEntity<Almoxarifado> {
        val almoxarifado = almoxarifadoService.atualizar(almoxarifadoVo, id)
        return ConstruirResposta.objeto(almoxarifado, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        almoxarifadoService.deletarPorId(id)
    }
}
