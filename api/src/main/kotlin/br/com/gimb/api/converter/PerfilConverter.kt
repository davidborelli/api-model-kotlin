package br.com.gimb.api.converter

import br.com.gimb.api.model.Perfil
import org.json.JSONArray
import org.json.JSONObject

class PerfilConverter {

    fun converterPerfils(firstArray: JSONArray): MutableList<Perfil> {
        val perfils = mutableListOf<Perfil>()

        for (elemento in firstArray) {
            perfils.add(convertePerfil(elemento as JSONObject))
        }

        return perfils
    }

    fun convertePerfil(objeto: JSONObject): Perfil {
        val perfil = Perfil()

        perfil.guid             = objeto.get("guid").toString()
        perfil.id               = objeto.getLong("profileId")
        perfil.nome             = objeto.get("description").toString()
        perfil.ativo            = if (objeto.get("active").toString() != "null") objeto.getBoolean("active") else false
        perfil.acessoWeb        = objeto.get("webAccess").toString()
        perfil.acessoApp        = objeto.get("appAccess").toString()
        perfil.configuracaoWeb  = objeto.get("configWeb").toString()
        perfil.configuracaoApp  = objeto.get("config").toString()
        perfil.cor             = objeto.get("colorId").toString()


        return perfil
    }
}
