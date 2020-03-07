package br.com.gimb.api.repository

import br.com.gimb.api.model.UsuarioImportacao
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsuarioImportacaoRepository: CrudRepository<UsuarioImportacao, Long> {
    fun findByAtivoAndExcluidoNull(ativo: Boolean): MutableList<UsuarioImportacao>
    fun findAllByExcluidoNull(): MutableList<UsuarioImportacao>
    fun findByIdAndExcluidoNull(id: Long): Optional<UsuarioImportacao>
    fun findByUsuario(usuario: String): Optional<UsuarioImportacao>
}
