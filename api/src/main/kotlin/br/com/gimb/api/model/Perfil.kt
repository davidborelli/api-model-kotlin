package br.com.gimb.api.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "perfil")
@Entity
class Perfil: BaseModel() {

    @Column(name = "nome")
    var nome: String = ""

    @Column(name = "ativo")
    var ativo: Boolean = true

    @Column(name = "acesso_web")
    var acessoWeb: String = ""

    @Column(name = "acesso_app")
    var acessoApp: String = ""

    @Column(name = "configuracao_web")
    var configuracaoWeb: String = ""

    @Column(name = "configuracao_app")
    var configuracaoApp: String = ""

    @Column(name = "cor")
    var cor: String = ""
}
