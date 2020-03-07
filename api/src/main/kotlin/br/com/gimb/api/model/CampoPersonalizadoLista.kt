package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "campo_personalizado_lista")
@Entity
class CampoPersonalizadoLista: BaseModel(){

    @ManyToOne
    @JoinColumn(name = "campo_personalizado_guid")
    var campoPersonalizado: CampoPersonalizado = CampoPersonalizado()

    @Column(name = "descricao")
    var descricao: String = ""

    @Column(name = "tipo_campo")
    var tipoCampo: String = ""

    @Column(name = "valores")
    var valores: String = ""
}
