package br.com.gimb.api.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "relatorio_despesa")
@Entity
class RelatorioDespesa: BaseModel() {

    @Column(name = "data_criacao")
    var dataCriacao: String = ""

    @Column(name = "data_finalizacao")
    var dataFinalizacao: String = ""

    @Column(name = "anotacao_despesa")
    var anotacaoDespesa: String = ""

    @Column(name = "data_expiracao_pagamento")
    var dataExpiracaoPagamento: String = ""

    @Column(name = "anotacao_pagamento")
    var anotacaoPagamento: String = ""

    @Column(name = "status")
    var status: String = ""

    @Column(name = "hash")
    var hash: String = ""

    @Column(name = "cliente_guid")
    var clienteId: Int = 0

    @Column(name = "usuario_guid")
    var usuarioId: Int = 0
}
