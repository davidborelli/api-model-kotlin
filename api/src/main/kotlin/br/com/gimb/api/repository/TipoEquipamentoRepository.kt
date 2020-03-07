package br.com.gimb.api.repository

import br.com.gimb.api.model.TipoEquipamento
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TipoEquipamentoRepository: CrudRepository<TipoEquipamento, Long> {
    fun findByAtivoAndExcluidoNull(ativo: Boolean): MutableList<TipoEquipamento>
    fun findAllByExcluidoNull(): MutableList<TipoEquipamento>
    fun findByIdAndExcluidoNull(id: Long): Optional<TipoEquipamento>
}
