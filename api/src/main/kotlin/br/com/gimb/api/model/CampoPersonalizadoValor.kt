package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "campo_personalizado_valor")
@Entity
class CampoPersonalizadoValor: BaseModel() {

    @ManyToOne
    @JoinColumn(name = "campo_personalizado_guid")
    var campoPersonalizado: CampoPersonalizado = CampoPersonalizado()

    @Column(name = "valor")
    var valor: String = ""

    @Column(name = "referencia_guid")
    var referencia_id: Int = 0

    @Column(name = "referencia_tipo")
    var referenciaTipo: String = ""
}
