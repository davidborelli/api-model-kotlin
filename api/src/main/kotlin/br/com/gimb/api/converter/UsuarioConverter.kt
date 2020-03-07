package br.com.gimb.api.converter

import br.com.gimb.api.model.Usuario
import org.json.JSONArray
import org.json.JSONObject

class UsuarioConverter {

    fun converterUsuarios(array: JSONArray): MutableList<Usuario> {
        val usuarios = mutableListOf<Usuario>()

        for (elemento in array) {
            usuarios.add(converteUsuario(elemento as JSONObject))
        }

        return usuarios
    }

    fun converteUsuario(objeto: JSONObject): Usuario {
        val usuario = Usuario()

        usuario.id             = objeto.getLong("userId")
        usuario.guid           = objeto.get("guid").toString()
        usuario.nome           = objeto.get("name").toString()
        usuario.usuario        = objeto.get("user").toString()
        usuario.dataNascimento = objeto.get("birthDate").toString()
        usuario.senha          = objeto.get("pass").toString()
        usuario.telefone1      = objeto.get("phone").toString()
        usuario.telefone2      = objeto.get("phone2").toString()
        usuario.usuarioWeb     = objeto.getBoolean("userWeb")
        usuario.email          = objeto.get("mail").toString()
        usuario.ativo          = objeto.getBoolean("active")
        usuario.cor            = objeto.get("colorId").toString()

        return usuario
    }
}
