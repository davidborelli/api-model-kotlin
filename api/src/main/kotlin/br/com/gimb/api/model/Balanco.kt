package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "balanco")
@Entity
class Balanco: BaseModel() {

    @Column(name = "data")
    var data: String = ""

    @Column(name = "valor")
    var valor: Double = 0.0

    @ManyToOne
    @JoinColumn(name = "usuario_guid")
    var usuario = Usuario()

    @ManyToOne
    @JoinColumn(name = "tipo_pagamento_guid")
    var tipoPagamento = TipoPagamento()
}
