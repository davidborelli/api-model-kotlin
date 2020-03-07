package br.com.gimb.api.repository

import br.com.gimb.api.model.Apontamento
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ApontamentoRepository: CrudRepository<Apontamento, Long> {
    fun findAllByExcluidoNull(): MutableList<Apontamento>
    fun findByIdAndExcluidoNull(id: Long): Optional<Apontamento>
}
