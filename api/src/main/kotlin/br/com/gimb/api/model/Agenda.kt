package br.com.gimb.api.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "agenda")
class Agenda: BaseModel() {

    @Column(name = "data")
    var data: LocalDate? = null

    @ManyToOne
    @JoinColumn(name = "usuario_guid")
    var usuario: Usuario = Usuario()

    @OneToMany(mappedBy = "agenda")
    var agendaItem= mutableListOf<AgendaItens>()
}
