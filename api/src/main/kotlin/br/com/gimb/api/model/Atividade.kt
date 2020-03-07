package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "atividade")
@Entity
class Atividade : BaseModel() {

        @Column(name = "nome")
        var nome: String = ""

        @Column(name = "cor")
        var cor: String = ""

        @Column(name = "ativo")
        var ativo: Boolean = true

        @Column(name = "tempo_improdutivo")
        var tempoImprodutivo: Boolean = false

        @Column(name = "operacional")
        var operacional: Boolean = false

        @Column(name = "financeiro")
        var financeiro: Boolean = false

        @Column(name = "apontamento")
        var apontamento: Boolean = false

        @Column(name = "checklist")
        var checklist: Boolean = false

        @Column(name = "solicitar_km")
        var solicitarKm: Boolean = false

}
