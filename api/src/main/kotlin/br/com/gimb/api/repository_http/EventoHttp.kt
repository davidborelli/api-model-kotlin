package br.com.gimb.api.repository_http

import br.com.gimb.api.converter.EventoConverter
import br.com.gimb.api.model.Evento
import khttp.responses.Response

class EventoHttp {

    fun pegarTodos(ativo: Boolean?): MutableList<Evento> {

        val token = LoginHttp.token
        val resposta: Response

        resposta = when (ativo) {
            null -> khttp.get(
                        url = "${LoginHttp.OLD_API}/event",
                        headers = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token"))

            else -> khttp.get(
                        url = "${LoginHttp.OLD_API}/eventByActive/$ativo",
                        headers = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token"))
        }

        when {
            resposta.statusCode == 200 -> {
                return EventoConverter().converterEventos(resposta.jsonArray)
            }
            resposta.statusCode == 401 -> {
                return LoginHttp.obterToken { pegarTodos(ativo) } as MutableList<Evento>
            }
            else -> throw Exception("Erro ao obter eventos")
        }
    }

    fun buscarPorId(id: Long): Evento? {
        val token = LoginHttp.token
        val resposta: Response

        resposta = khttp.get(url = "${LoginHttp.OLD_API}/event/$id",
                            headers = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token"))

        when {
            resposta.statusCode == 200 -> {
                return EventoConverter().converteEvento(resposta.jsonObject)
            }
            resposta.statusCode == 401 -> {
                return LoginHttp.obterToken { buscarPorId(id) } as Evento
            }
            else -> throw Exception("Erro ao obte evento")
        }
    }
}
