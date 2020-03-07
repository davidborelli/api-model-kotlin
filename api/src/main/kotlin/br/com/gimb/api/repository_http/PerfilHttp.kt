package br.com.gimb.api.repository_http

import br.com.gimb.api.converter.PerfilConverter
import br.com.gimb.api.model.Perfil
import khttp.responses.Response

class PerfilHttp {

    fun pegarTodos(): MutableList<Perfil> {

        val token = LoginHttp.token
        val respostaPerfil: Response
        val header = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token")

        respostaPerfil = khttp.get(
                        url = "${LoginHttp.OLD_API}/profile",
                        headers = header)


        when {
            respostaPerfil.statusCode == 200 -> {
                return PerfilConverter().converterPerfils(respostaPerfil.jsonArray)
            }
            respostaPerfil.statusCode == 401 -> {
                return LoginHttp.obterToken { pegarTodos() } as MutableList<Perfil>
            }
            else -> throw Exception("Erro ao obter Perfil")
        }
    }

    fun buscarPorId(id: Long): Perfil? {
        val token = LoginHttp.token
        val header = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token")
        val respostaPerfil: Response

        respostaPerfil = khttp.get(url = "${LoginHttp.OLD_API}/profile/$id",
                            headers = header)


        when {
            respostaPerfil.statusCode == 200 -> {
                return PerfilConverter().convertePerfil(respostaPerfil.jsonObject)
            }
            respostaPerfil.statusCode == 401 -> {
                return LoginHttp.obterToken { buscarPorId(id) } as Perfil
            }
            else -> throw Exception("Erro ao obte cliente")
        }
    }
}
