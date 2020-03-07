package br.com.gimb.api.repository

import br.com.gimb.api.model.Usuario
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsuarioRepository: CrudRepository<Usuario, Long> {
    fun findByAtivoAndExcluidoNull(ativo: Boolean): MutableList<Usuario>
    fun findAllByExcluidoNull(): MutableList<Usuario>
    fun findByIdAndExcluidoNull(id: Long): Optional<Usuario>
    fun findByUsuario(usuario: String): Optional<Usuario>
}
