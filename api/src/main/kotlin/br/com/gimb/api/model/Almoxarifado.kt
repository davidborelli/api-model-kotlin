package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "almoxarifado")
@Entity
class Almoxarifado : BaseModel() {

    @Column(name = "nome")
    var nome: String = ""

    @Column(name = "ativo")
    var ativo: Boolean = true

    @Column(name = "cor")
    var cor: String = ""
}
