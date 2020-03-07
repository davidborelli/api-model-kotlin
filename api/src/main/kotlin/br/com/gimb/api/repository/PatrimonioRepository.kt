package br.com.gimb.api.repository

import br.com.gimb.api.model.Patrimonio
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PatrimonioRepository: CrudRepository<Patrimonio, Long> {
    fun findByAtivoAndExcluidoNull(ativo: Boolean): MutableList<Patrimonio>
    fun findAllByExcluidoNull(): MutableList<Patrimonio>
    fun findByIdAndExcluidoNull(id: Long): Optional<Patrimonio>
}
