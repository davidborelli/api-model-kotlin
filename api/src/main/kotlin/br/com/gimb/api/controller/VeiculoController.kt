package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Veiculo
import br.com.gimb.api.model.vo.VeiculoVO
import br.com.gimb.api.repository.VeiculoRepository
import br.com.gimb.api.repository_http.VeiculoHttp
import br.com.gimb.api.services.VeiculoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/veiculos")
@RestController
class VeiculoController {

    @Autowired
    lateinit var veiculoRepository: VeiculoRepository

    @Autowired
    lateinit var veiculoService: VeiculoService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<Veiculo>>{
        val listaVeiculo : MutableList<Veiculo>?

        if (true) {
            listaVeiculo = VeiculoHttp().pegarTodos()
        } else {
            listaVeiculo = veiculoService.buscarTodos()
        }
        return ConstruirResposta.lista(listaVeiculo, HttpMethod.GET)
    }

    @PostMapping("/importacao")
    fun salvarApiNova(@RequestBody listaVeiculos: MutableList<Veiculo>): ResponseEntity<MutableList<Veiculo>> {
        veiculoRepository.saveAll(listaVeiculos)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<Veiculo> {
        val veiculo = veiculoService.buscarPorId(id)
        return ConstruirResposta.objeto(veiculo, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody veiculoVO: VeiculoVO): ResponseEntity<Veiculo> {
        val veiculo = veiculoService.salvar(veiculoVO)
        return ConstruirResposta.objeto(veiculo, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody veiculoVO: VeiculoVO, @PathVariable id: Long): ResponseEntity<Veiculo> {
        val veiculo = veiculoService.atualizar(veiculoVO, id)
        return ConstruirResposta.objeto(veiculo, HttpMethod.POST)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        veiculoService.deletar(id)
    }

}

class VeiculoParaConversao {
    val id: Long? = null
    val guid: String = ""
    val marca: String = ""
    val anoFabricacao: Int? = null
    val modelo: String = ""
    val anoModelo: Int? = null
    val placa: String = ""
}
