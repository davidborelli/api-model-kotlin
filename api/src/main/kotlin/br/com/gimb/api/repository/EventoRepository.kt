package br.com.gimb.api.repository

import br.com.gimb.api.model.Evento
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EventoRepository: CrudRepository<Evento, Long> {
    fun findByAtivoAndExcluidoNull(ativo: Boolean): MutableList<Evento>
    fun findAllByExcluidoNull(): MutableList<Evento>
    fun findByIdAndExcluidoNull(id: Long): Optional<Evento>
}
