package br.com.gimb.api.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "")
@Entity
class CampoDescricoes: BaseModel() {

        @Column(name = "descricao")
        var descricao: String = ""

        @Column(name = "tipo")
        var tipo: String = ""

        @Column(name = "valor_default")
        var valorDefault: String? = null

        @Column(name = "obrigatorio")
        var obrigatorio: Boolean = true

        @Column(name = "referencia")
        var referencia: Long = 0
}
