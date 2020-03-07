package br.com.gimb.api

import br.com.gimb.api.model.Almoxarifado
import br.com.gimb.api.model.vo.AlmoxarifadoVO
import br.com.gimb.api.repository.AlmoxarifadoRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

class AlmoxarifadoControllerTest: ApiApplicationTests() {

    var uri = "$server/almoxarifados"

    @Autowired
    lateinit var almoxarifadoRepository: AlmoxarifadoRepository

    @Before
    override fun setup() {
        super.setup()
    }

    @Test
    @Throws(Exception::class)
    fun salvarItens(){
        almoxarifadoRepository.deleteAll()

        val listaAlmoxarifadoParaCriar = listOf(
                AlmoxarifadoVO("Teste1", "cor1", true),
                AlmoxarifadoVO("Teste2", "cor2", true),
                AlmoxarifadoVO("Teste3", "cor3", true),
                AlmoxarifadoVO("Teste4", "cor4", true),
                AlmoxarifadoVO("Teste5", "cor5", true),
                AlmoxarifadoVO("Teste6", "cor6", true),
                AlmoxarifadoVO("Teste7", "cor7", true),
                AlmoxarifadoVO("Teste8", "cor8", true),
                AlmoxarifadoVO("Teste9", "cor9", true),
                AlmoxarifadoVO("Teste10", "cor10", false)
        )

        listaAlmoxarifadoParaCriar.forEach {
            p -> mvc?.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(p)))
                ?.andExpect{r ->  assertEquals(r.response.status, 200)}
                ?.andExpect{r ->  assertEquals(mapFromJson(r.response.contentAsString, Almoxarifado::class.java).criado.isNullOrEmpty(), false)}
        }
    }

    @Test
    fun excluir() {

        val listaAlmoxarifadoLocalizados = buscaTodosItens()
//        val idExcluir = listaAlmoxarifadoLocalizados[3].id

//        val uriExcluir = "$uri/$idExcluir"

//        val result = mvc?.perform(MockMvcRequestBuilders.delete(uriExcluir))?.andReturn()

//        val listaAlmoxarifadoLocalizados = buscaTodosItens()
//        val idExcluir = listaAlmoxarifadoLocalizados[3].id
//
//        val uriExcluir = "$uri/$idExcluir"
//
//        val result =  mvc?.perform(MockMvcRequestBuilders.delete(uriExcluir))?.andReturn()
//
//        assertEquals(200, result?.response?.status)
    }

    @Test
    fun atualizar() {

        val almoxarifadoParaAtualizar = AlmoxarifadoVO("TesteAtualizacao", "corTeste", false)
        val jsonAlmoxarifadoParaAtualizar = super.mapToJson(almoxarifadoParaAtualizar)

        val listaAlmoxarifadoLocalizados = buscaTodosItens()
//        val primeiroId = listaAlmoxarifadoLocalizados[0].id

//        val uriAtualizar = "$uri/$primeiroId"

//        val mvcResult = mvc?.perform(MockMvcRequestBuilders.put(uriAtualizar)
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .content(jsonAlmoxarifadoParaAtualizar))
//                ?.andReturn()

//        val almoxarifadoAtualizado = super.mapFromJson(mvcResult?.response?.contentAsString, Almoxarifado::class.java)

//        assertEquals(almoxarifadoAtualizado.id, primeiroId)
//        assertEquals(almoxarifadoAtualizado.nome, "TesteAtualizacao")
//        assertEquals(almoxarifadoAtualizado.ativo, false)
//        assertEquals(almoxarifadoAtualizado.cor, "corTeste")
    }

    @Test
    fun buscarTodos() {
        val listaAlmoxarifadoLocalizados = buscaTodosItens()
        val listaAlmoxarifadoAtivo = buscaTodosItensAtivos()
        val listaAlmoxarifadoInativo = buscaTodosItensInativos()

        assertEquals(9, listaAlmoxarifadoLocalizados.size)
        assertEquals(7, listaAlmoxarifadoAtivo.size)
        assertEquals(2, listaAlmoxarifadoInativo.size)
    }

    private fun buscaTodosItens(): Array<Almoxarifado> {
        val mvcResult = mvc?.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                ?.andReturn()

        val content = mvcResult?.response?.contentAsString
        return super.mapFromJson(content, Array<Almoxarifado>::class.java)
    }

    private fun buscaTodosItensAtivos(): Array<Almoxarifado> {
        val uriTodosAtivos = "$uri?ativo=true"
        val mvcResult = mvc?.perform(MockMvcRequestBuilders.get(uriTodosAtivos)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                ?.andReturn()

        val content = mvcResult?.response?.contentAsString
        return super.mapFromJson(content, Array<Almoxarifado>::class.java)
    }

    private fun buscaTodosItensInativos(): Array<Almoxarifado> {
        val uriTodosAtivos = "$uri?ativo=false"
        val mvcResult = mvc?.perform(MockMvcRequestBuilders.get(uriTodosAtivos)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                ?.andReturn()

        val content = mvcResult?.response?.contentAsString
        return super.mapFromJson(content, Array<Almoxarifado>::class.java)
    }
}
