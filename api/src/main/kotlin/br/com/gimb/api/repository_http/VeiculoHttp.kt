package br.com.gimb.api.repository_http

import br.com.gimb.api.converter.VeiculosConverter
import br.com.gimb.api.model.Veiculo
import khttp.responses.Response

class VeiculoHttp {

    fun pegarTodos(): MutableList<Veiculo> {

        val token = LoginHttp.token
        val respostaVeiculo: Response
        val header = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token")

        respostaVeiculo = khttp.get(url = "${LoginHttp.OLD_API}/vehicle", headers = header)

        when {
            respostaVeiculo.statusCode == 200 -> {
                return VeiculosConverter().converterVeiculos(respostaVeiculo.jsonArray)
            }
            respostaVeiculo.statusCode == 401 -> {
                return LoginHttp.obterToken { pegarTodos() } as MutableList<Veiculo>
            }
            else -> throw Exception("Erro ao obter Veiculos")
        }
    }

    fun buscarPorId(id: Long): Veiculo {
        val token = LoginHttp.token
        val header = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token")
        val respostaVeiculo: Response

        respostaVeiculo = khttp.get(url = "${LoginHttp.OLD_API}/vehicle/$id", headers = header)

        when {
            respostaVeiculo.statusCode == 200 -> {
                return VeiculosConverter().converteVeiculo(respostaVeiculo.jsonObject)
            }
            respostaVeiculo.statusCode == 401 -> {
                return LoginHttp.obterToken { buscarPorId(id) } as Veiculo
            }
            else -> throw Exception("Erro ao obte cliente")
        }
    }
}
