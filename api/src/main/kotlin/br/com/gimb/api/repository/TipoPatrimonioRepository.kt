package br.com.gimb.api.repository

import br.com.gimb.api.model.TipoPatrimonio
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TipoPatrimonioRepository: CrudRepository<TipoPatrimonio, Long> {
    fun findByAtivoAndExcluidoNull(ativo: Boolean): MutableList<TipoPatrimonio>
    fun findAllByExcluidoNull(): MutableList<TipoPatrimonio>
    fun findByIdAndExcluidoNull(id: Long): Optional<TipoPatrimonio>
}
