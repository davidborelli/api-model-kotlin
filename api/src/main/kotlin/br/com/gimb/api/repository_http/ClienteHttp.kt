package br.com.gimb.api.repository_http

import br.com.gimb.api.converter.ClienteConverter
import br.com.gimb.api.model.Cliente
import khttp.responses.Response

class ClienteHttp {

    fun pegarTodos(ativo: Boolean?): MutableList<Cliente> {

        val token = LoginHttp.token
        val resposta: Response

        resposta = when (ativo) {
            null -> khttp.get(
                        url = "${LoginHttp.OLD_API}/client",
                        headers = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token"))

            else -> khttp.get(
                        url = "${LoginHttp.OLD_API}/clientByActive/$ativo",
                        headers = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token"))
        }

        when {
            resposta.statusCode == 200 -> {
                return ClienteConverter().converterClientes(resposta.jsonArray)
            }
            resposta.statusCode == 401 -> {
                return LoginHttp.obterToken { pegarTodos(ativo) } as MutableList<Cliente>
            }
            else -> throw Exception("Erro ao obter clientes")
        }
    }

    fun buscarPorId(id: Long): Cliente? {
        val token = LoginHttp.token
        val resposta: Response

        resposta = khttp.get(url = "${LoginHttp.OLD_API}/client/$id",
                            headers = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token"))

        when {
            resposta.statusCode == 200 -> {
                return ClienteConverter().converteClienteVeiculo(resposta.jsonObject)
            }
            resposta.statusCode == 401 -> {
                return LoginHttp.obterToken { buscarPorId(id) } as Cliente
            }
            else -> throw Exception("Erro ao obte cliente")
        }
    }
}
