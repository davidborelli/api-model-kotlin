package br.com.gimb.api.repository

import br.com.gimb.api.model.Observacao
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ObservacaoRepository: CrudRepository<Observacao, Long> {
    fun findByAtivoAndExcluidoNull(ativo: Boolean): MutableList<Observacao>
    fun findAllByExcluidoNull(): MutableList<Observacao>
    fun findByIdAndExcluidoNull(id: Long): Optional<Observacao>
}
