package br.com.gimb.api.model

import javax.persistence.*

@Table(name = "rota")
@Entity
class Rota: BaseModel() {

    @Column(name = "latitude")
    var latitude: String = ""

    @Column(name = "longitude")
    var longitude: String = ""

    @Column(name = "hora")
    var hora: String = ""

    @Column(name = "status")
    var status: String = ""

    @ManyToOne
    @JoinColumn(name = "usuario_guid")
    var usuario: Usuario = Usuario()
}
