package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "despesa")
@Entity
class Despesa: BaseModel() {

    @Column(name = "valor")
    var valor: Double = 0.0

    @Column(name = "data")
    var data: String = ""

    @Column(name = "latitude")
    var latitude: String = ""

    @Column(name = "longitude")
    var longitude: String = ""

    @Column(name = "anotacao")
    var anotacao: String = ""

    @Column(name = "num_forma_pagamento")
    var numFormaPagamento: String = ""

    @ManyToOne
    @JoinColumn(name = "cliente_guid")
    var cliente: Cliente = Cliente()

    @ManyToOne
    @JoinColumn(name = "evento_guid")
    var evento: Evento = Evento()

    @ManyToOne
    @JoinColumn(name = "usuario_guid")
    var usuario: Usuario = Usuario()

    @ManyToOne
    @JoinColumn(name = "tipo_pagamento_guid")
    var tipoPagamento: TipoPagamento = TipoPagamento()

}
