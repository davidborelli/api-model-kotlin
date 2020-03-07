package br.com.gimb.api.repository

import br.com.gimb.api.model.Servico
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ServicoRepository: CrudRepository<Servico, Long> {
    fun findAllByExcluidoNull(): MutableList<Servico>
    fun findByIdAndExcluidoNull(id: Long): Optional<Servico>
}
