package br.com.gimb.api.converter

import br.com.gimb.api.model.Veiculo
import org.json.JSONArray
import org.json.JSONObject

class VeiculosConverter {

    fun converterVeiculos(firstArray: JSONArray): MutableList<Veiculo> {
        val veiculos = mutableListOf<Veiculo>()

        for (elemento in firstArray) {
            veiculos.add(converteVeiculo(elemento as JSONObject))
        }

        return veiculos
    }

    fun converteVeiculo(objeto: JSONObject): Veiculo {
        val veiculo = Veiculo()

        veiculo.id            = objeto.getLong("vehicleId")
        veiculo.guid          = objeto.get("guid").toString()
        veiculo.marca         = objeto.get("brand").toString()
        veiculo.anoFabricacao = objeto.getInt("fabricationYear")
        veiculo.modelo        = objeto.get("model").toString()
        veiculo.anoModelo     = objeto.getInt("modelYear")
        veiculo.placa         = objeto.get("plate").toString()

        return veiculo
    }
}
