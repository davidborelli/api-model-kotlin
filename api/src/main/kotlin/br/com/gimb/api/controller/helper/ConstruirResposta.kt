package br.com.gimb.api.controller.helper

import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ConstruirResposta {

    companion object {
        fun <T> objeto(objeto: T?, verboHttp: HttpMethod): ResponseEntity<T> {
            return if (objeto != null)
                ResponseEntity.ok(objeto)
            else
                when (verboHttp.name) {
                    "GET" -> ResponseEntity.notFound().build()
                    else ->  ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                }
        }

        fun <T> lista(listaObjeto: MutableList<T>?, verboHttp: HttpMethod): ResponseEntity<MutableList<T>> {
            return if (listaObjeto != null && listaObjeto.size > 0)
                ResponseEntity.ok(listaObjeto)
            else
                when (verboHttp.name) {
                    "GET" -> ResponseEntity.notFound().build()
                    else ->  ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                }
        }
    }



}
