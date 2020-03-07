package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "foto_personalizado")
@Entity
class FotoPersonalizado: BaseModel() {

    @ManyToOne
    @JoinColumn(name = "campo_personalizado_guid")
    var campoPersonalizado: CampoPersonalizado = CampoPersonalizado()

    @Column(name = "referencia_guid")
    var referenciaId: Int = 0

    @Column(name = "referencia_tipo")
    var referenciaTipo: String = ""

    @Column(name = "latitude")
    var latitude: String = ""

    @Column(name = "longitude")
    var longitude: String = ""

    @Column(name = "foto_extensao")
    var fotoExtensao: String = ""

    @Column(name = "fix_foto_extensao")
    var fixFotoExtensao: String = ""

    @Column(name = "foto_nome")
    var fotoNome: String = ""

    @Column(name = "fix_foto_nome")
    var fixFotoNome: String = ""

    @Column(name = "foto_caminho")
    var fotoCaminho: String = ""

    @Column(name = "fix_foto_caminho")
    var fixFotoCaminho: String = ""

    @Column(name = "mensagem")
    var mensagem: String = ""

    @Column(name = "manualmente")
    var manualmente: Boolean = false

    @Column(name = "observacao")
    var observacao: String = ""

    @Column(name = "status_checklist")
    var status_checklist: Boolean = false

    @Column(name = "status_fix")
    var statusFix: Boolean = false
}
