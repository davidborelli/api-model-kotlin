package br.com.gimb.api.repository

import br.com.gimb.api.model.FotoPersonalizado
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FotoPersonalizadoRepository: CrudRepository<FotoPersonalizado, Long> {
    fun findAllByExcluidoNull(): MutableList<FotoPersonalizado>
    fun findByIdAndExcluidoNull(id: Long): Optional<FotoPersonalizado>
}
