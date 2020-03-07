package br.com.gimb.api.repository.agenda

import br.com.gimb.api.model.Agenda
import br.com.gimb.api.repository.filter.AgendaFiltro

interface AgendaRepositoryQuery {

    fun filtrar(agendaFiltro: AgendaFiltro): MutableList<Agenda>

}
