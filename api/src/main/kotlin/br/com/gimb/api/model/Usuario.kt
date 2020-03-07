package br.com.gimb.api.model

import javax.persistence.*
import kotlin.jvm.Transient

@Table(name = "usuario")
@Entity
class Usuario: BaseModel() {

    @Column(name = "nome")
    var nome: String = ""

    @Column(name = "usuario")
    var usuario: String = ""

    @Column(name = "data_nascimento")
    var dataNascimento: String = ""

    @Column(name = "senha")
    var senha: String = ""

    @Column(name = "telefone1")
    var telefone1: String = ""

    @Column(name = "telefone2")
    var telefone2: String = ""

    @Column(name = "usuario_web")
    var usuarioWeb: Boolean = false

    @Column(name = "email")
    var email: String = ""

    @Column(name = "ativo")
    var ativo: Boolean = true

    @Column(name = "cor")
    var cor: String = ""

    @Transient
    var perfil: String = "asd"

}
