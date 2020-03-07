package br.com.gimb.api.repository

import br.com.gimb.api.model.CampoPersonalizadoLista
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CampoPersonalizadoListaRepository: CrudRepository<CampoPersonalizadoLista, Long> {
    fun findAllByExcluidoNull(): MutableList<CampoPersonalizadoLista>
    fun findByIdAndExcluidoNull(id: Long): Optional<CampoPersonalizadoLista>
}
