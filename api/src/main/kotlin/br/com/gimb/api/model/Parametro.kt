package br.com.gimb.api.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "parametros")
@Entity
class Parametro: BaseModel() {
    @Column(name = "agenda_tem_domingo")
    var agendaTemDomingo: Boolean = false;

    @Column(name = "agenda_lancamento_retro")
    var agendaLancamentoRetro: Boolean = false;
}
