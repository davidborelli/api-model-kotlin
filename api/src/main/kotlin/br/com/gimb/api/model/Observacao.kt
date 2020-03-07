package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "observacao")
@Entity
class Observacao : BaseModel() {

    @Column(name = "nome")
    var nome: String = ""

    @Column(name = "inventario")
    var inventario: Boolean = false

    @Column(name = "ativo")
    var ativo: Boolean = true

}
