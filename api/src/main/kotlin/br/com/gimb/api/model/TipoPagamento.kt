package br.com.gimb.api.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "tipo_pagamento")
@Entity
class TipoPagamento: BaseModel() {

    @Column(name = "nome")
    var nome: String = ""

    @Column(name = "consome_saldo")
    var consomeSaldo: Boolean = false

    @Column(name = "solicita_identificador")
    var solicitaIdentificador: Boolean = false

    @Column(name = "ativo")
    var ativo: Boolean = true

    @Column(name = "cor")
    var cor: String = ""
}
