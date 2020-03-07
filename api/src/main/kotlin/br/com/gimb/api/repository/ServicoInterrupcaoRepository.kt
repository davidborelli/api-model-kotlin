package br.com.gimb.api.repository

import br.com.gimb.api.model.ServicoInterrupcao
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ServicoInterrupcaoRepository: CrudRepository<ServicoInterrupcao, Long> {
    fun findAllByExcluidoNull(): MutableList<ServicoInterrupcao>
    fun findByIdAndExcluidoNull(id: Long): Optional<ServicoInterrupcao>
}
