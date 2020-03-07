package br.com.gimb.api.converter

import br.com.gimb.api.model.Atividade
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class AtividadeConverter {

    fun converterAtividades(firstArray: JSONArray, secondArray: JSONArray): MutableList<Atividade> {
        val atividades = mutableListOf<Atividade>()

        for (elemento in firstArray) {
            atividades.add(converteAtividade(elemento as JSONObject))
        }

        for (elemento in secondArray) {
            atividades.add(converteEvento(elemento as JSONObject))
        }

        return atividades
    }

    private fun converteAtividade(objeto: JSONObject): Atividade {
        val atividade = Atividade()

        atividade.id               = objeto.getLong("actionId")
        atividade.guid             = objeto.get("guid").toString()
        atividade.nome             = objeto.get("description").toString()
        atividade.checklist        = if(objeto.get("checklist").toString() != "null") objeto.getBoolean("checklist") else false
        atividade.cor              = objeto.get("colorId").toString()
        atividade.ativo            = if (objeto.get("active").toString() != "null") objeto.getBoolean("active") else false
        atividade.tempoImprodutivo = false
        atividade.operacional      = true
        atividade.financeiro       = false
        atividade.apontamento      = false
        atividade.solicitarKm      = false

        return atividade
    }

    private fun converteEvento(objeto: JSONObject): Atividade {
        val atividade = Atividade()

        atividade.id               = objeto.getLong("eventId")
        atividade.guid             = objeto.get("guid").toString()
        atividade.nome             = objeto.get("description").toString()
        atividade.tempoImprodutivo = if (objeto.get("improdutiveTime").toString() != "null") objeto.getBoolean("improdutiveTime") else false
        atividade.financeiro       = if (objeto.get("appearsOn").toString() != "null") objeto.get("appearsOn").toString().contains("LQ") else false
        atividade.apontamento      = if (objeto.get("appearsOn").toString() != "null") objeto.get("appearsOn").toString().contains("OS") else false
        atividade.cor              = objeto.get("colorId").toString()
        atividade.ativo            = if (objeto.get("active").toString() != "null") objeto.getBoolean("active") else false
        atividade.operacional      = false
        atividade.checklist        = false
        atividade.solicitarKm      = if (objeto.get("requestKm").toString() != "null") objeto.getBoolean("requestKm") else false

        return atividade
    }

    fun converteParaAtividade(atividade: JSONObject?, evento: JSONObject?): MutableList<Atividade>? {
        val atividades: MutableList<Atividade> = ArrayList()

        if (atividade != null)
            atividades.add(converteAtividade(atividade))

        if (evento!= null)
            atividades.add(converteEvento(evento))

        return atividades
    }
}
