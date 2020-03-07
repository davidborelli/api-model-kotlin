package br.com.gimb.api.repository

import br.com.gimb.api.model.Despesa
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DespesaRepository: CrudRepository<Despesa, Long> {
    fun findAllByExcluidoNull(): MutableList<Despesa>
    fun findByIdAndExcluidoNull(id: Long): Optional<Despesa>
}
