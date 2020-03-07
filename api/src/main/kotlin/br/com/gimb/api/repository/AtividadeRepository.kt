package br.com.gimb.api.repository

import br.com.gimb.api.model.Atividade
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AtividadeRepository: CrudRepository <Atividade, Long> {
    fun findByAtivoAndExcluidoNull(ativo: Boolean): MutableList<Atividade>
    fun findAllByExcluidoNull(): MutableList<Atividade>
    fun findByIdAndExcluidoNull(id: Long): Optional<Atividade>
}
