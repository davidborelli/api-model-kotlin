package br.com.gimb.api.repository

import br.com.gimb.api.model.TipoPagamento
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TipoPagamentoRepository: CrudRepository<TipoPagamento, Long> {
    fun findByAtivoAndExcluidoNull(ativo: Boolean): MutableList<TipoPagamento>
    fun findAllByExcluidoNull(): MutableList<TipoPagamento>
    fun findByIdAndExcluidoNull(id: Long): Optional<TipoPagamento>
}
