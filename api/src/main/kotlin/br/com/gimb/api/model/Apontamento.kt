package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "apontamento")
@Entity
class Apontamento: BaseModel() {

    @Column(name = "tempo_inicial")
    var tempoInicial: String = ""

    @Column(name = "tempo_final")
    var tempoFinal: String = ""

    @Column(name = "tempo_inicial_lat")
    var tempoInicialLat: String = ""

    @Column(name = "tempo_inicial_lgt")
    var tempoInicialLgt: String = ""

    @Column(name = "tempo_final_lat")
    var tempoFinalLat: String = ""

    @Column(name = "tempo_final_lgt")
    var tempoFinalLgt: String = ""

    @Column(name = "status")
    var status: String = ""

    @ManyToOne
    @JoinColumn(name = "cliente_guid")
    var cliente: Cliente = Cliente()

    @ManyToOne
    @JoinColumn(name = "usuario_guid")
    var usuario: Usuario = Usuario()

    @Column(name = "tempo_inicial_original")
    var tempoInicialOriginal: String = ""

    @Column(name = "tempo_final_original")
    var tempoFinalOriginal: String = ""

    @Column(name = "anotacao")
    var anotacao: String = ""
}
