package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.enumarated.CampoPersonalizadoReferenciaEnum
import br.com.gimb.api.model.CampoPersonalizado
import br.com.gimb.api.model.vo.CampoPersonalizadoVO
import br.com.gimb.api.services.CampoPersonalizadoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/campos-personalizados")
class CampoPersonalizadoController {

    @Autowired
    lateinit var campoPersonalizadoService: CampoPersonalizadoService

    @GetMapping
    fun buscarTodos(): ResponseEntity<MutableList<CampoPersonalizado>> {
        val listaCampoPersonalizado = campoPersonalizadoService.buscarTodos()
        return ConstruirResposta.lista(listaCampoPersonalizado, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorIr(@PathVariable id: Long): ResponseEntity<CampoPersonalizado>{
        val campoPersonalizado = campoPersonalizadoService.buscarPorId(id)
        return ConstruirResposta.objeto(campoPersonalizado, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody campoPersonalizadoVo: CampoPersonalizadoVO): ResponseEntity<CampoPersonalizado> {
        val campoPersonalizado = campoPersonalizadoService.salvar(campoPersonalizadoVo, CampoPersonalizadoReferenciaEnum.ATIVIDADE)
        return ConstruirResposta.objeto(campoPersonalizado, HttpMethod.POST)
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody campoPersonalizadoVo: CampoPersonalizadoVO, @PathVariable id: Long): ResponseEntity<CampoPersonalizado> {
        val campoPersonalizado = campoPersonalizadoService.atualizar(campoPersonalizadoVo, CampoPersonalizadoReferenciaEnum.ATIVIDADE, id)
        return ConstruirResposta.objeto(campoPersonalizado, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        campoPersonalizadoService.deletar(id)
    }
}
