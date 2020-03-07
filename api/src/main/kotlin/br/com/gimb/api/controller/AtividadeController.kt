package br.com.gimb.api.controller

import br.com.gimb.api.controller.helper.ConstruirResposta
import br.com.gimb.api.model.Atividade
import br.com.gimb.api.model.vo.AtividadeVO
import br.com.gimb.api.repository.AtividadeRepository
import br.com.gimb.api.repository_http.AtividadeHttp
import br.com.gimb.api.services.AtividadeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/atividades")
class AtividadeController {

    @Autowired
    lateinit var atividadeService: AtividadeService

    @Autowired
    lateinit var atividadeRepository: AtividadeRepository

    @GetMapping
    fun buscarTodos(@RequestParam("ativo", required = false) ativo: Boolean?): ResponseEntity<MutableList<Atividade>> {
        val listaAtividade: MutableList<Atividade>?

        if (true) {
            listaAtividade = AtividadeHttp().pegarTodos(ativo)
        }
        else {
            listaAtividade = atividadeService.buscarTodos(ativo)
        }

        return ConstruirResposta.lista(listaAtividade, HttpMethod.GET)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): ResponseEntity<MutableList<Atividade>> {
        val atividadeEncontradas: MutableList<Atividade>?

        if (true){
            atividadeEncontradas = AtividadeHttp().buscarPorId(id)
        }
        else {
            atividadeEncontradas = null
        }

        return ConstruirResposta.lista(atividadeEncontradas, HttpMethod.GET)
    }

    @PostMapping
    fun salvar(@RequestBody atividadeVo: AtividadeVO): ResponseEntity<Atividade>{
        val atividadeSalva: Atividade = atividadeService.salvar(atividadeVo)
        return ConstruirResposta.objeto(atividadeSalva, HttpMethod.POST)
    }

    @PostMapping("/importacao")
    fun salvarApiNova(@RequestBody listaAtividades: MutableList<Atividade>): ResponseEntity<MutableList<Atividade>>{
        atividadeRepository.saveAll(listaAtividades)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @PutMapping("/{id}")
    fun atualizar(@RequestBody atividadeVo: AtividadeVO, @PathVariable id: Long): ResponseEntity<Atividade> {
        val atividadeAtualizada: Atividade = atividadeService.atualizar(atividadeVo, id)
        return ConstruirResposta.objeto(atividadeAtualizada, HttpMethod.PUT)
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long) {
        atividadeService.deletarPorId(id)
    }
}
