package br.com.gimb.api.model


import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "tipo_patrimonio")
@Entity
class TipoPatrimonio: BaseModel() {

    @Column(name = "nome")
    var nome: String = ""

    @Column(name = "ativo")
    var ativo: Boolean = true

    @Column(name = "cor")
    var cor: String = ""

}
