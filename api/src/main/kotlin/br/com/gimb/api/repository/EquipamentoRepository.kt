package br.com.gimb.api.repository

import br.com.gimb.api.model.Equipamento
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EquipamentoRepository: CrudRepository<Equipamento, Long> {
    fun findAllByExcluidoNull(): MutableList<Equipamento>
    fun findByIdAndExcluidoNull(id: Long): Optional<Equipamento>
}
