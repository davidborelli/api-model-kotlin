package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "patrimonio")
@Entity
class Patrimonio: BaseModel() {

    @Column(name = "nome")
    var nome: String = ""

    @Column(name = "identificador")
    var identificador: String = ""

    @Column(name = "numero_serial")
    var numeroSerial: String = ""

    @Column(name = "ativo")
    var ativo: Boolean = true

    @ManyToOne
    @JoinColumn(name = "almoxarifado_guid")
    var almoxarifado: Almoxarifado = Almoxarifado()

    @Column(name = "localizacao")
    var localizacao: String = ""

    @Column(name = "numero_patrimonio")
    var numeroPatrimonio: Int = 0

    @ManyToOne
    @JoinColumn(name = "tipo_patrimonio_guid")
    var tipoPatrimonio: TipoPatrimonio = TipoPatrimonio()
}
