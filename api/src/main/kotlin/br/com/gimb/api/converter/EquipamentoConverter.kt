package br.com.gimb.api.converter

import br.com.gimb.api.model.Equipamento
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class EquipamentoConverter {

    fun converterEquipamentos(firstArray: JSONArray): MutableList<Equipamento> {
        val equipamentos = mutableListOf<Equipamento>()

        for (elemento in firstArray) {
            equipamentos.add(converteEquipamento(elemento as JSONObject))
        }

        return equipamentos
    }

    fun converteEquipamento(objeto: JSONObject): Equipamento {
        val equipamento = Equipamento()

        equipamento.guid = objeto.get("guid").toString()
        equipamento.nome = objeto.get("description").toString()

        return equipamento
    }
}
