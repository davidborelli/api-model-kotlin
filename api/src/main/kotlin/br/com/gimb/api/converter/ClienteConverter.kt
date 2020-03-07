package br.com.gimb.api.converter

import br.com.gimb.api.model.Cliente
import org.json.JSONArray
import org.json.JSONObject

class ClienteConverter {

    fun converterClientes(array: JSONArray): MutableList<Cliente> {
        val clientes = mutableListOf<Cliente>()

        for (elemento in array) {
            clientes.add(converteClienteVeiculo(elemento as JSONObject))
        }

        return clientes
    }

    fun converteClienteVeiculo(objeto: JSONObject): Cliente {
        val cliente = Cliente()
        cliente.id          = objeto.getInt("clientId").toLong()
        cliente.guid        = objeto.get("guid").toString()
        cliente.razaoSocial = objeto.get("companyName").toString()
        cliente.nome        = objeto.get("tradingName").toString()
        cliente.cep         = objeto.get("zipCode").toString()
        cliente.endereco    = objeto.get("address").toString()
        cliente.senhaAcesso = objeto.get("pass").toString()
        cliente.cidade      = objeto.get("city").toString()
        cliente.cor         = objeto.get("colorId").toString()
        cliente.cnpj_cpf    = objeto.get("document").toString()
        cliente.ativo       = objeto.getBoolean("active")
        cliente.telefone    = objeto.get("phone").toString()
        cliente.ieRg        = objeto.get("registration").toString()

        if (objeto.getJSONArray("vehicles") != null) {
            cliente.veiculos = mutableListOf()

            for (veiculoObject in objeto.getJSONArray("vehicles")) {
                cliente.veiculos.add(converteVeiculo(veiculoObject as JSONObject))
            }
        } else {
            cliente.veiculos = mutableListOf()
        }

        return cliente
    }

    private fun converteVeiculo(objeto: JSONObject) : VeiculoConverter {
        val veiculo = VeiculoConverter()

        veiculo.guid          = objeto.get("guid").toString()
        veiculo.marca         = objeto.get("brand").toString()
        veiculo.modelo        = objeto.get("model").toString()
        veiculo.identificador = objeto.get("plate").toString()

        return veiculo
    }
}

class VeiculoConverter {
    var guid: String = ""
    var marca: String = ""
    var modelo: String = ""
    var identificador: String = ""
}
