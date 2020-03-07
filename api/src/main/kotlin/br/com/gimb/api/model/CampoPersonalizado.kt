package br.com.gimb.api.model

import br.com.gimb.api.enumarated.CampoPersonalizadoReferenciaEnum
import br.com.gimb.api.enumarated.TipoCampoPersonalizadoEnum
import javax.persistence.*

@Table(name = "campo_personalizado")
@Entity
class CampoPersonalizado: BaseModel() {

    @Column(name = "tipo_campo")
    @Enumerated(EnumType.STRING)
    var tipoCampo = TipoCampoPersonalizadoEnum.TEXTO

    @Column(name = "descricao")
    var descricao: String = ""

    @Column(name = "referencia_guid")
    var idReferencia: Int = 0

    @Column(name = "referencia_tipo")
    var tipoReferencia  = CampoPersonalizadoReferenciaEnum.NENHUM

}
