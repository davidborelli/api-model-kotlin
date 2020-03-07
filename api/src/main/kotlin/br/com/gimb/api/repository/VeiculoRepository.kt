package br.com.gimb.api.repository

import br.com.gimb.api.model.Veiculo
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface VeiculoRepository: CrudRepository<Veiculo, Long> {
    fun findAllByExcluidoNull(): MutableList<Veiculo>
    fun findByIdAndExcluidoNull(id: Long): Optional<Veiculo>
}
