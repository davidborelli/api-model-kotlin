package br.com.gimb.api.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "cliente")
@Entity
class Cliente: BaseModel() {

    @Column(name = "nome")
    var nome: String = ""

    @Column(name = "razao_social")
    var razaoSocial: String = ""

    @Column(name = "ie_rg")
    var ieRg: String = ""

    @Column(name = "cnpj_cpf")
    var cnpj_cpf: String = ""

    @Column(name = "endereco")
    var endereco: String = ""

    @Column(name = "telefone")
    var telefone: String = ""

    @Column(name = "cep")
    var cep: String = ""

    @Column(name = "cidade")
    var cidade: String = ""

    @Column(name = "senha_acesso")
    var senhaAcesso: String = ""

    @Column(name = "ativo")
    var ativo: Boolean = true

    @Column(name = "cor")
    var cor: String = ""

//    @JsonIgnore
    @Transient
//    @OneToMany(mappedBy = "veiculo")
    var veiculos = mutableListOf<Any>()

}

