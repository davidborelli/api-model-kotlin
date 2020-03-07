package br.com.gimb.api.repository

import br.com.gimb.api.model.CampoDescricoes
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CampoDescricoesRepository: CrudRepository<CampoDescricoes, Long> {
    fun findAllByExcluidoNull(): MutableList<CampoDescricoes>
    fun findByIdAndExcluidoNull(id: Long): Optional<CampoDescricoes>
}
