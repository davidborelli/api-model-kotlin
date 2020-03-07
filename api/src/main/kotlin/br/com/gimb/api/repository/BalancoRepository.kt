package br.com.gimb.api.repository

import br.com.gimb.api.model.Balanco
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BalancoRepository: CrudRepository<Balanco, Long> {
    fun findAllByExcluidoNull(): MutableList<Balanco>
    fun findByIdAndExcluidoNull(id: Long): Optional<Balanco>
}
