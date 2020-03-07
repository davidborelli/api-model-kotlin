package br.com.gimb.api.model

import javax.persistence.*


@Table(name = "patrimonio_historico")
@Entity
class PatrimonioHistorico: BaseModel() {

    @ManyToOne
    @JoinColumn(name = "patrimonio_guid")
    var patrimonio: Patrimonio = Patrimonio()

    @Column(name = "data")
    var data: String = ""

    @Column(name = "localizacao")
    var localizacao: String = ""

    @ManyToOne
    @JoinColumn(name = "usuario_guid")
    var usuario: Usuario = Usuario()

    @Column(name = "observacao")
    var observacao: String = ""
}
