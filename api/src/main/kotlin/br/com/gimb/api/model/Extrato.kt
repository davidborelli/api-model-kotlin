package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "extrato")
@Entity
class Extrato: BaseModel() {

    @Column(name = "dataLiberacao")
    var dataLiberacao: String =  ""

    @Column(name = "tipo")
    var tipo: String = ""

    @Column(name = "valor")
    var valor: Double = 0.0

    @ManyToOne
    @JoinColumn(name = "despesa_guid")
    var despesa: Despesa = Despesa()

    @ManyToOne
    @JoinColumn(name = "tipo_pagamento_guid")
    var tipoPagamento: TipoEquipamento = TipoEquipamento()

    @Column(name = "status_pagamento")
    var statusPagamento: String = ""

    @Column(name = "data_criacao")
    var dataCriacao: String = ""

    @ManyToOne
    @JoinColumn(name = "usuario_criou_guid")
    var usuarioCriou: Usuario = Usuario()

    @Column(name = "data_aprovacao")
    var data_aprovacao: String = ""

    @ManyToOne
    @JoinColumn(name = "usuario_aprovou")
    var usuarioAprovou: Usuario = Usuario()

    @ManyToOne
    @JoinColumn(name = "usuario_guid")
    var usuario: Usuario = Usuario()

    @Column(name = "informacao")
    var informacao: String = ""

    @Column(name = "observacao")
    var observacao: String = ""
}
