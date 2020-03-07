package br.com.gimb.api.repository

import br.com.gimb.api.model.Agenda
import br.com.gimb.api.repository.agenda.AgendaRepositoryQuery
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AgendaRepository: CrudRepository<Agenda, String>, AgendaRepositoryQuery {
    fun findByGuid(guid: String): Optional<Agenda>
}
