package br.com.gimb.api.repository

import br.com.gimb.api.model.Perfil
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PerfilRepository: CrudRepository<Perfil, Long> {
    fun findByAtivoAndExcluidoNull(ativo: Boolean): MutableList<Perfil>
    fun findAllByExcluidoNull(): MutableList<Perfil>
    fun findByIdAndExcluidoNull(id: Long): Optional<Perfil>
}
