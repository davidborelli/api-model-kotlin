//package br.com.gimb.api.controller
//
//import br.com.gimb.api.controller.helper.ConstruirResposta
//import br.com.gimb.api.model.ApontamentoEvento
//import br.com.gimb.api.model.vo.ApontamentoEventoVO
//import br.com.gimb.api.repository_http.EventoHttp
//import br.com.gimb.api.services.ApontamentoEventoService
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.http.HttpMethod
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.*
//
//@RestController
//@RequestMapping("/api/eventos")
//class EventoController {
//
//    @Autowired
//    lateinit var apontamentoEventoService: ApontamentoEventoService
//
//    @GetMapping
//    fun buscarTodos(): ResponseEntity<MutableList<ApontamentoEvento>> {
//        val listaApontamentoEvento: MutableList<ApontamentoEvento>
//
//        if (true) {
//            listaApontamentoEvento = EventoHttp().pegarTodos()
//        }
//        else {
//
//        }
//        return ConstruirResposta.lista(listaApontamentoEvento, HttpMethod.GET)
//    }
//
//    @GetMapping("/{id}")
//    fun buscarPorId(@PathVariable id: Long): ResponseEntity<ApontamentoEvento>{
//        val apontamentoEvento = apontamentoEventoService.buscarPorId(id)
//        return ConstruirResposta.objeto(apontamentoEvento, HttpMethod.GET)
//    }
//
//    @PostMapping
//    fun salvar(@RequestBody apontamentoEventoVO: ApontamentoEventoVO): ResponseEntity<ApontamentoEvento>{
//        val apontamentoEvento = apontamentoEventoService.salvar(apontamentoEventoVO)
//        return ConstruirResposta.objeto(apontamentoEvento, HttpMethod.POST)
//    }
//
//    @PutMapping("/{id}")
//    fun atualizar(@RequestBody apontamentoEventoVO: ApontamentoEventoVO, @PathVariable id: Long): ResponseEntity<ApontamentoEvento>{
//        val apontamentoEvento = apontamentoEventoService.atualizar(apontamentoEventoVO, id)
//        return ConstruirResposta.objeto(apontamentoEvento, HttpMethod.PUT)
//    }
//
//    @DeleteMapping
//    fun deletar(@PathVariable id: Long) {
//        apontamentoEventoService.deletarPorId(id)
//    }
//}
