package br.com.gimb.api.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "tipo_equipamento")
@Entity
class TipoEquipamento: BaseModel() {

    @Column(name = "nome")
    var nome: String = ""

    @Column(name = "tempo_estimado")
    var tempoEstimado: Double = 0.0

    @Column(name = "ativo")
    var ativo: Boolean = true

}
