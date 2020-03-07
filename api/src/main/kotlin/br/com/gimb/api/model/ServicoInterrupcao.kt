package br.com.gimb.api.model

import javax.persistence.*

@Entity
@Table(name = "servico_interrupcao")
class ServicoInterrupcao: BaseModel() {

    @Column(name = "tempo_inicial")
    var tempoInicial: String = ""

    @Column(name = "tempo_final")
    var tempoFinal: String = ""

    @Column(name = "tempo_inicial_lat")
    var tempoInicialLat: String = ""

    @Column(name = "tempo_final_lat")
    var tempoFinalLat: String = ""

    @Column(name = "tempo_inicial_lgt")
    var tempoInicialLgt: String = ""

    @Column(name = "tempo_final_lgt")
    var tempoFinalLgt: String = ""

    @ManyToOne
    @JoinColumn(name = "servico_guid")
    var servico: Servico = Servico()
}
