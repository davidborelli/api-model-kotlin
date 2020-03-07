package br.com.gimb.api.repository

import br.com.gimb.api.model.PatrimonioHistorico
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PatrimonioHistoricoRepository: CrudRepository<PatrimonioHistorico, Long> {
    fun findAllByExcluidoNull(): MutableList<PatrimonioHistorico>
    fun findByIdAndExcluidoNull(id: Long): Optional<PatrimonioHistorico>
}
