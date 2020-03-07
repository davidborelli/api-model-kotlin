package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "apontamento_evento")
@Entity
class ApontamentoEvento: BaseModel() {

    @Column(name = "data_hora")
    var dataHora: String = ""

    @Column(name = "latitude")
    var latitude: String = ""

    @Column(name = "longitude")
    var longitude: String = ""

    @ManyToOne
    @JoinColumn(name = "apontamento_guid")
    var apontamento: Apontamento = Apontamento()

    @ManyToOne
    @JoinColumn(name = "evento_guid")
    var evento: Evento = Evento()

    @Column(name = "tempo_inicial")
    var tempoInicial: String = ""

    @Column(name = "tempo_final")
    var tempoFinal: String = ""

    @Column(name = "tempo_inicial_original")
    var tempoInicialOriginal: String = ""

    @Column(name = "tempo_final_original")
    var tempoFinalOriginal: String = ""

    @Column(name = "observacao")
    var observacao: String = ""

}
