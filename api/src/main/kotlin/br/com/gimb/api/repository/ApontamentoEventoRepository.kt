package br.com.gimb.api.repository

import br.com.gimb.api.model.ApontamentoEvento
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ApontamentoEventoRepository: CrudRepository<ApontamentoEvento, Long> {
    fun findAllByExcluidoNull(): MutableList<ApontamentoEvento>
    fun findByIdAndExcluidoNull(id: Long): Optional<ApontamentoEvento>
}
