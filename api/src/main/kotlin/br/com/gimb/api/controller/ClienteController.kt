package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Cliente
import br.com.gimb.api.model.vo.ClienteVO
import br.com.gimb.api.repository.ClienteRepository
import br.com.gimb.api.repository_http.ClienteHttp
import br.com.gimb.api.services.ClienteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/clientes")
@RestController
class ClienteController {

    @Autowired
    lateinit var clienteService: ClienteService

    @Autowired
    lateinit var clienteRepository: ClienteRepository

    @GetMapping
    fun buscarTodos(@RequestParam("ativo", required = false) ativo: Boolean?): ResponseEntity<MutableList<Cliente>>{
        val clientes: MutableList<Cliente>?

        if (true) {
            clientes = ClienteHttp().pegarTodos(ativo)
        }
        else {
            clientes = clienteService.buscarTodos(ativo)
        }

        return ConstruirResposta.lista(clientes, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Cliente>{
        val cliente: Cliente?

        if (true) {
            cliente = ClienteHttp().buscarPorId(id)
        }
        else {
            cliente = clienteService.buscarPorId(id)
        }

        return ConstruirResposta.objeto(cliente, HttpMethod.GET)
    }

    @PostMapping("/importacao")
    fun salvarApiNova(@RequestBody listaClientes: MutableList<Cliente>): ResponseEntity<MutableList<Cliente>> {
        clienteRepository.saveAll(listaClientes)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @PostMapping
    fun salvar(@RequestBody clienteVO: ClienteVO): ResponseEntity<Cliente> {
        val cliente = clienteService.salvar(clienteVO)
        return ConstruirResposta.objeto(cliente, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody clienteVO: ClienteVO, @PathVariable id: Long): ResponseEntity<Cliente> {
        val cliente = clienteService.atualizar(clienteVO, id)
        return ConstruirResposta.objeto(cliente, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        clienteService.deletar(id)
    }
}
