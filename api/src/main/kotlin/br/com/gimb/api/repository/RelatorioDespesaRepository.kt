package br.com.gimb.api.repository

import br.com.gimb.api.model.RelatorioDespesa
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RelatorioDespesaRepository: CrudRepository<RelatorioDespesa, Long> {
    fun findAllByExcluidoNull(): MutableList<RelatorioDespesa>
    fun findByIdAndExcluidoNull(id: Long): Optional<RelatorioDespesa>
}
