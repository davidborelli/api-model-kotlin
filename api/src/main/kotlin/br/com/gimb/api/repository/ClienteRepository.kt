package br.com.gimb.api.repository

import br.com.gimb.api.model.Cliente
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ClienteRepository: CrudRepository<Cliente, String> {
    fun findByAtivoAndExcluidoNull(ativo: Boolean): MutableList<Cliente>
    fun findAllByExcluidoNull(): MutableList<Cliente>
    fun findByGuidAndExcluidoNull(id: Long): Optional<Cliente>
}
