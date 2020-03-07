package br.com.gimb.api.repository

import br.com.gimb.api.model.CampoPersonalizado
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CampoPersonalizadoRepository: CrudRepository<CampoPersonalizado, Long> {
    fun findAllByExcluidoNull(): MutableList<CampoPersonalizado>
    fun findByIdAndExcluidoNull(id: Long): Optional<CampoPersonalizado>
}
