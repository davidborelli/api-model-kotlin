package br.com.gimb.api.repository_http

import br.com.gimb.api.converter.UsuarioConverter
import br.com.gimb.api.model.Usuario
import khttp.responses.Response

class UsuarioHttp {

    fun pegarTodos(ativo: Boolean?): MutableList<Usuario> {

        val token = LoginHttp.token
        val resposta: Response

        resposta = when (ativo) {
            null -> khttp.get(
                        url = "${LoginHttp.OLD_API}/user",
                        headers = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token"))

            else -> khttp.get(
                        url = "${LoginHttp.OLD_API}/userByActive/$ativo",
                        headers = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token"))
        }

        when {
            resposta.statusCode == 200 -> {
                return UsuarioConverter().converterUsuarios(resposta.jsonArray)
            }
            resposta.statusCode == 401 -> {
                return LoginHttp.obterToken { pegarTodos(ativo) } as MutableList<Usuario>
            }
            else -> throw Exception("Erro ao obter clientes")
        }
    }

    fun buscarPorId(id: Long): Usuario? {
        val token = LoginHttp.token
        val resposta: Response

        resposta = khttp.get(url = "${LoginHttp.OLD_API}/user/$id",
                            headers = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token"))

        when {
            resposta.statusCode == 200 -> {
                return UsuarioConverter().converteUsuario(resposta.jsonObject)
            }
            resposta.statusCode == 401 -> {
                return LoginHttp.obterToken { buscarPorId(id) } as Usuario
            }
            else -> throw Exception("Erro ao obte cliente")
        }
    }
}
