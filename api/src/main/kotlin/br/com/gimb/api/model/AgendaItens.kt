package br.com.gimb.api.model

import javax.persistence.*

@Entity
@Table(name = "agenda_item")
class AgendaItens: BaseModel() {

    @Column(name = "agenda_guid")
    var agenda: String? = ""

    @ManyToOne
    @JoinColumn(name = "cliente_guid")
    var cliente: Cliente? = Cliente()

    @ManyToOne
    @JoinColumn(name = "atividade_guid")
    var atividade: Atividade? = Atividade()

    @ManyToOne
    @JoinColumn(name = "veiculo_guid")
    var veiculo: Veiculo? = Veiculo()

    @Column(name = "tipo")
    var tipo: String = ""

    @Column(name = "status")
    var status: String = ""

    @Column(name = "observacao")
    var observacao: String = ""
}
