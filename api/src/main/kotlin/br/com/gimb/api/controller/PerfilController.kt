package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Perfil
import br.com.gimb.api.model.vo.PerfilVO
import br.com.gimb.api.repository_http.PerfilHttp
import br.com.gimb.api.services.PerfilService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/perfil")
@RestController
class PerfilController {

    @Autowired
    lateinit var perfilService: PerfilService

    @GetMapping
    fun buscarTodos(@RequestParam("ativo", required = false) ativo: Boolean): ResponseEntity<MutableList<Perfil>>{
        val listaPerfil = perfilService.buscarTodos(ativo)
        return ConstruirResposta.lista(listaPerfil, HttpMethod.GET)
    }

    @GetMapping("/importacao")
    fun perfilsApiAntigaConvertido(): ResponseEntity<MutableList<Perfil>>? {
        val listaPerfil: MutableList<Perfil>

        if (true) {
            listaPerfil = PerfilHttp().pegarTodos()
        }
        else {
            listaPerfil = perfilService.buscarTodos(null)
        }

        return ConstruirResposta.lista(listaPerfil, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Perfil> {
        val perfil = perfilService.buscarPorId(id)
        return ConstruirResposta.objeto(perfil, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody perfilVO: PerfilVO): ResponseEntity<Perfil> {
        val perfil = perfilService.salvar(perfilVO)
        return ConstruirResposta.objeto(perfil, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody perfilVO: PerfilVO, @PathVariable id: Long): ResponseEntity<Perfil> {
        val perfil = perfilService.atualizar(perfilVO, id)
        return ConstruirResposta.objeto(perfil, HttpMethod.POST)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        perfilService.deletar(id)
    }

}
