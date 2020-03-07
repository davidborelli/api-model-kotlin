package br.com.gimb.api.repository

import br.com.gimb.api.model.Extrato
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ExtratoRepository: CrudRepository<Extrato, Long> {
    fun findAllByExcluidoNull(): MutableList<Extrato>
    fun findByIdAndExcluidoNull(id: Long): Optional<Extrato>
}
