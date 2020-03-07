package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "veiculo")
@Entity
class Veiculo: BaseModel() {

    @Column(name = "marca")
    var marca: String = ""

    @Column(name = "ano_fabricacao")
    var anoFabricacao: Int = 0

    @Column(name = "modelo")
    var modelo: String = ""

    @Column(name = "ano_modelo")
    var anoModelo: Int = 0

    @Column(name = "placa")
    var placa: String = ""

    @Column(name = "tempo_estimado")
    var tempoEstimado: Double = 0.0

//    @ManyToOne
//    @JoinColumn(name = "cliente_guid")
    @Column(name = "cliente_guid")
    var cliente: String = ""

//    @ManyToOne
//    @JoinColumn(name = "equipamento_guid")
    @Column(name = "equipamento_guid")
    var equipamento: String = ""
}
