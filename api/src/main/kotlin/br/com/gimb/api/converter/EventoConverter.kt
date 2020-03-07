package br.com.gimb.api.converter

import br.com.gimb.api.model.Evento
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class EventoConverter {

    fun converterEventos(array: JSONArray): MutableList<Evento> {
        val eventos = mutableListOf<Evento>()

        for (elemento in array) {
            eventos.add(converteEvento(elemento as JSONObject))
        }

        return eventos
    }

    fun converteEvento(objeto: JSONObject): Evento {
        val evento = Evento()

        evento.id               = objeto.getLong("eventId")
        evento.guid             = objeto.get("guid").toString()
        evento.nome             = objeto.get("description").toString()
        evento.tempoImprodutivo = objeto.getBoolean("improdutiveTime")
        evento.ativo            = objeto.getBoolean("active")

        return evento
    }
}
