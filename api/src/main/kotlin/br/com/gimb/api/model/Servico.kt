package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "servico")
@Entity
class Servico: BaseModel() {

    @Column(name = "tempo_inicio")
    var tempoInicio: String = ""

    @Column(name = "tempo_fim")
    var tempoFim: String = ""

    @Column(name = "tempo_inicio_lat")
    var tempoInicioLat: String = ""

    @Column(name = "tempo_fim_lat")
    var tempoFimLat: String = ""

    @Column(name = "tempo_inicio_lgt")
    var tempoInicioLgt: String = ""

    @Column(name = "tempo_fim_lgt")
    var tempoFimLgt: String = ""

    @Column(name = "status")
    var status: String = ""

    @ManyToOne
    @JoinColumn(name = "cliente_guid")
    var cliente: Cliente = Cliente()

    @ManyToOne
    @JoinColumn(name = "usuario_guid")
    var usuario: Usuario = Usuario()

    @ManyToOne
    @JoinColumn(name = "atividade_guid")
    var atividade: Atividade = Atividade()

    @Column(name = "tag")
    var tag: String = ""

    @ManyToOne
    @JoinColumn(name = "veiculo_guid")
    var veiculo: Veiculo = Veiculo()

}
