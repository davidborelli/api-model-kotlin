package br.com.gimb.api.model

import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
open class BaseModel {

    @Id
    @Column(name = "guid")
    var guid: String = ""

    @Column(name = "id")
    var id: Long? = null

    @Column(name = "criado")
    var criado: String? = null

    @Column(name = "atualizado")
    var atualizado: String? = null

    @Column(name = "excluido")
    var excluido: String? = null
}
