package br.com.gimb.api.repository

import br.com.gimb.api.model.Rota
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RotaRepository: CrudRepository<Rota, Long> {
    fun findAllByExcluidoNull(): MutableList<Rota>
    fun findByIdAndExcluidoNull(id: Long): Optional<Rota>
}
