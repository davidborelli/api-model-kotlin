package br.com.gimb.api.repository

import br.com.gimb.api.model.AgendaItens
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AgendaItensRepository: CrudRepository<AgendaItens, String> {
    fun findAllByGuid(guid: String): MutableList<AgendaItens>
    fun findByAgenda(guid: String): MutableList<AgendaItens>
    fun findByGuid(guid: String): Optional<AgendaItens>
}
