package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Empresa
import br.com.gimb.api.model.vo.EmpresaVO
import br.com.gimb.api.services.EmpresaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/empresas")
class EmpresaController {

    @Autowired
    lateinit var empresaService: EmpresaService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<Empresa>> {
        val listaEmpresas = empresaService.buscarTodos()
        return ConstruirResposta.lista(listaEmpresas, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Empresa>{
        val empresa = empresaService.buscarPorId(id)
        return ConstruirResposta.objeto(empresa, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody empresaVO: EmpresaVO): ResponseEntity<Empresa> {
        val empresa = empresaService.salvar(empresaVO)
        return ConstruirResposta.objeto(empresa, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody empresaVO: EmpresaVO, @PathVariable id: Long): ResponseEntity<Empresa> {
        val empresa = empresaService.atualizar(empresaVO, id)
        return ConstruirResposta.objeto(empresa, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun excluir(@PathVariable id: Long) {
        empresaService.deletar(id)
    }
}
