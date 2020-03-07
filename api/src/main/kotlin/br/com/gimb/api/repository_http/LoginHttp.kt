package br.com.gimb.api.repository_http

class LoginHttp {

    companion object {

        var token: String? = null

//        const val OLD_API: String = "http://multartec.gimb.com.br/apiGIMB/admin"
        const val OLD_API: String = "http://localhost:8080/apiGIMB/admin"

        fun obterToken() {
            obterToken {  }
        }


        fun obterToken(completion: ((token: String) -> Any?)?): Any? {
            val resposta = khttp.post(
//                    url = "http://multartec.gimb.com.br/apiGIMB/token",
                    url = "http://localhost:8080/apiGIMB/token",
                    headers = mapOf("Content-Type" to "application/json"),
                    json = mapOf("user" to "gimb.admin", "pass" to "admin@")
            )
            if (resposta.statusCode == 202) {
                token = resposta.jsonObject["access_token"].toString()

                if (completion != null) {
                    return completion(token!!)
                }

                return null
            } else
                throw Exception("Erro ao obter Token")
        }

    }

}
