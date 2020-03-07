package br.com.gimb.api.repository

import br.com.gimb.api.model.Almoxarifado
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AlmoxarifadoRepository: CrudRepository<Almoxarifado, Long> {
    fun findByAtivoAndExcluidoNull(ativo: Boolean): MutableList<Almoxarifado>
    fun findAllByExcluidoNull(): MutableList<Almoxarifado>
    fun findByIdAndExcluidoNull(id: Long): Optional<Almoxarifado>
}
