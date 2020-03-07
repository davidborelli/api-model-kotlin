package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "equipamento")
@Entity
class Equipamento: BaseModel() {

    @Column(name = "nome")
    var nome: String = ""

    @ManyToOne
    @JoinColumn(name = "tipo_equipamento_guid")
    var tipoEquipamento: TipoEquipamento = TipoEquipamento()

    @Column(name = "marca")
    var marca: String = ""

    @Column(name = "identificador")
    var identificador: String = ""

    @Column(name = "modelo")
    var modelo: String = ""

    @Column(name = "ano_modelo")
    var anoModelo: Int = 0

    @Column(name = "ano_fabricacao")
    var anoFabricacao: Int = 0

    @ManyToOne
    @JoinColumn(name = "cliente_guid")
    var cliente: Cliente = Cliente()
}
