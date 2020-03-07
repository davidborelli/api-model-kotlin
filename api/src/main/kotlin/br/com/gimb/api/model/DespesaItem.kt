package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "despesa_item")
@Entity
class DespesaItem: BaseModel() {

    @Column(name = "data")
    var data: String = ""

    @Column(name = "anotacao")
    var anotacao: String = ""

    @Column(name = "quantidade")
    var quantidade: Double = 0.0

    @Column(name = "valor_unitario")
    var valorUnitario: Double = 0.0

    @Column(name = "valor_total")
    var valorTotal: Double = 0.0

    @ManyToOne
    @JoinColumn(name = "evento_guid")
    var evento: Evento = Evento()

    @ManyToOne
    @JoinColumn(name = "usuario_guid")
    var usuario: Usuario = Usuario()

    @ManyToOne
    @JoinColumn(name =  "relatorio_despesa_guid")
    var relatorioDespesa: RelatorioDespesa = RelatorioDespesa()

    @ManyToOne
    @JoinColumn(name = "despesa_guid")
    var despesa: Despesa = Despesa()
}
