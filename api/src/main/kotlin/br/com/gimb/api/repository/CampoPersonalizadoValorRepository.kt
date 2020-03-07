package br.com.gimb.api.repository

import br.com.gimb.api.model.CampoPersonalizadoValor
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CampoPersonalizadoValorRepository: CrudRepository<CampoPersonalizadoValor, Long> {
    fun findAllByExcluidoNull(): MutableList<CampoPersonalizadoValor>
    fun findByIdAndExcluidoNull(id: Long): Optional<CampoPersonalizadoValor>
}
