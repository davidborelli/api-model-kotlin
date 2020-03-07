package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "evento")
@Entity
class Evento : BaseModel() {

    @Column(name = "nome")
    var nome: String = ""

    @Column(name = "tempo_improdutivo")
    var tempoImprodutivo: Boolean = false

    @Column(name = "operacional", columnDefinition = "bool", nullable = true)
    var os: Boolean = false

    @Column(name = "financeiro", columnDefinition = "bool", nullable = true)
    var liquidacao: Boolean = false

    @Column(name = "ativo", columnDefinition = "bool", nullable = false)
    var ativo: Boolean = true

}
