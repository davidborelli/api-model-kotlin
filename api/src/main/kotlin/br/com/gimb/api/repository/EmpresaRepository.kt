package br.com.gimb.api.repository

import br.com.gimb.api.model.Empresa
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmpresaRepository: CrudRepository<Empresa, Long> {
    fun findAllByExcluidoNull(): MutableList<Empresa>
    fun findByIdAndExcluidoNull(id: Long): Optional<Empresa>
}
