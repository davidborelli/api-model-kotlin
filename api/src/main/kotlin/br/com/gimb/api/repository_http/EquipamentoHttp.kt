package br.com.gimb.api.repository_http

import br.com.gimb.api.converter.EquipamentoConverter
import br.com.gimb.api.model.Equipamento
import khttp.responses.Response

class EquipamentoHttp {

    fun pegarTodos(): MutableList<Equipamento> {

        val token = LoginHttp.token
        val respostaEquipamento: Response
        val header = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token")

        respostaEquipamento = khttp.get(url = "${LoginHttp.OLD_API}/equipment", headers = header)

        when {
            respostaEquipamento.statusCode == 200 -> {
                return EquipamentoConverter().converterEquipamentos(respostaEquipamento.jsonArray)
            }
            respostaEquipamento.statusCode == 401 -> {
                return LoginHttp.obterToken { pegarTodos() } as MutableList<Equipamento>
            }
            else -> throw Exception("Erro ao obter Atividaes")
        }
    }

    fun buscarPorId(id: Long): Equipamento {
        val token = LoginHttp.token
        val header = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token")
        val respostaEquipamento: Response

        respostaEquipamento = khttp.get(url = "${LoginHttp.OLD_API}/equipment/$id", headers = header)

        when {
            respostaEquipamento.statusCode == 200 -> {
                return EquipamentoConverter().converteEquipamento(respostaEquipamento.jsonObject)
            }
            respostaEquipamento.statusCode == 401 -> {
                return LoginHttp.obterToken { buscarPorId(id) } as Equipamento
            }
            else -> throw Exception("Erro ao obte cliente")
        }
    }
}
