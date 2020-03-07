package br.com.gimb.api.repository

import br.com.gimb.api.model.DespesaItem
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DespesaItemRepository: CrudRepository<DespesaItem, Long> {
    fun findAllByExcluidoNull(): MutableList<DespesaItem>
    fun findByIdAndExcluidoNull(id: Long): Optional<DespesaItem>
}
