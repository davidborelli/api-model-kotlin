package br.com.gimb.api.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "empresa")
@Entity
class Empresa: BaseModel() {

    @Column(name = "razao_social")
    var razaoSocial: String = ""

    @Column(name = "nome_fantasia")
    var nomeFantasia: String = ""

    @Column(name = "endereco")
    var endereco: String = ""

    @Column(name = "cidade")
    var cidade: String = ""

    @Column(name = "cnpj")
    var cnpj: String = ""

    @Column(name = "ie")
    var ie: String = ""

    @Column(name = "telefone")
    var telefone: String = ""

    @Column(name = "site")
    var site: String = ""

    @Column(name = "cep")
    var cep: String = ""

    @Column(name = "nome_logo")
    var nomeLogo: String = ""
}
