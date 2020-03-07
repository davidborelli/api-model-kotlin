package br.com.gimb.api.repository_http

import br.com.gimb.api.converter.AtividadeConverter
import br.com.gimb.api.model.Atividade
import khttp.responses.Response

class AtividadeHttp {

    fun pegarTodos(ativo: Boolean?): MutableList<Atividade> {

        val token = LoginHttp.token
        val respostaAcao: Response
        val respostaEvento: Response
        val header = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token")

        respostaAcao = when (ativo) {
            null -> khttp.get(
                    url = "${LoginHttp.OLD_API}/action",
                    headers = header)

            else -> khttp.get(
                    url = "${LoginHttp.OLD_API}/actionByActive/$ativo",
                    headers = header)
        }

        respostaEvento = when (ativo) {
            null -> khttp.get(
                    url = "${LoginHttp.OLD_API}/event",
                    headers = header)

            else -> khttp.get(
                    url = "${LoginHttp.OLD_API}/eventByActive/$ativo",
                    headers = header)
        }

        when {
            respostaAcao.statusCode == 200 && respostaEvento.statusCode == 200 -> {
                return AtividadeConverter().converterAtividades(respostaAcao.jsonArray, respostaEvento.jsonArray)
            }
            respostaAcao.statusCode == 401 || respostaEvento.statusCode == 401 -> {
                return LoginHttp.obterToken { pegarTodos(ativo) } as MutableList<Atividade>
            }
            else -> throw Exception("Erro ao obter Atividaes")
        }
    }

    fun buscarPorId(id: Long): MutableList<Atividade>? {
        val token = LoginHttp.token
        val header = mapOf("Content-Type" to "application/json", "Authorization" to "Bearer $token")
        val respostaAtividade: Response
        val respostaEvento: Response

        respostaAtividade = khttp.get(url = "${LoginHttp.OLD_API}/action/$id",
                            headers = header)

        respostaEvento = khttp.get(url = "${LoginHttp.OLD_API}/event/$id",
                            headers = header)

        when {
            respostaAtividade.statusCode == 200 && respostaEvento.statusCode == 200 -> {
                return AtividadeConverter().converteParaAtividade(respostaAtividade.jsonObject, respostaEvento.jsonObject)
            }
            respostaAtividade.statusCode == 401 || respostaEvento.statusCode == 401 -> {
                return LoginHttp.obterToken { buscarPorId(id) } as MutableList<Atividade>
            }
            else -> throw Exception("Erro ao obte cliente")
        }
    }
}
