package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Usuario
import br.com.gimb.api.model.UsuarioImportacao
import br.com.gimb.api.model.vo.UsuarioVO
import br.com.gimb.api.repository.UsuarioImportacaoRepository
import br.com.gimb.api.repository_http.UsuarioHttp
import br.com.gimb.api.services.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/usuarios")
@RestController
class UsuarioController {

    @Autowired
    lateinit var usuarioService: UsuarioService

    @Autowired
    lateinit var usuarioImportacaoResponseEntity: UsuarioImportacaoRepository

    @GetMapping
    fun buscarTodos(@RequestParam("ativo", required = false) ativo: Boolean?): ResponseEntity<MutableList<Usuario>>{
        val listaUsuario: MutableList<Usuario>?

        if (true) {
            listaUsuario = UsuarioHttp().pegarTodos(ativo)
        }
        else {
            listaUsuario = usuarioService.buscarTodos(ativo)
        }

        return ConstruirResposta.lista(listaUsuario, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Usuario> {
        val usuario: Usuario?

        if (true) {
            usuario = UsuarioHttp().buscarPorId(id)
        }
        else {
            usuario = usuarioService.buscarPorId(id)
        }

        return ConstruirResposta.objeto(usuario, HttpMethod.GET)
    }

    @PostMapping("/importacao")
    fun salvarApiNova(@RequestBody usuarios: MutableList<UsuarioImportacao>): ResponseEntity<MutableList<UsuarioImportacao>> {
        usuarioImportacaoResponseEntity.saveAll(usuarios)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @PostMapping
    fun salvar(@RequestBody usuarioVO: UsuarioVO): ResponseEntity<Usuario> {
        val usuario = usuarioService.salvar(usuarioVO)
        return ConstruirResposta.objeto(usuario, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody usuarioVO: UsuarioVO, @PathVariable id: Long): ResponseEntity<Usuario> {
        val usuario = usuarioService.atualizar(usuarioVO, id)
        return ConstruirResposta.objeto(usuario, HttpMethod.POST)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        usuarioService.deletar(id)
    }

}
