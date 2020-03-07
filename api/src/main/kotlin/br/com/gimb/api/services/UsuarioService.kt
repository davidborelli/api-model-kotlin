package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Usuario
import br.com.gimb.api.model.vo.UsuarioVO
import br.com.gimb.api.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService {

    @Autowired
    lateinit var usuarioRepository: UsuarioRepository

    fun buscarTodos(ativo: Boolean?): MutableList<Usuario> {
        return when (ativo){
            null -> usuarioRepository.findAllByExcluidoNull()
            else -> usuarioRepository.findByAtivoAndExcluidoNull(ativo)
        }
    }

    fun buscarPorUsuario(usuario: String): Optional<Usuario>? {
        val optional = usuarioRepository.findByUsuario(usuario)
        if (optional.isPresent)
            return optional
        return null
    }

    fun buscarPorId(id: Long): Usuario? {
        val optional = usuarioRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(usuarioVO: UsuarioVO): Usuario {

//        if (usuarioVO.perfil.toInt() == 0 )
//            throw Exception("Campo perfil é obrigatório")

        val usuario = Usuario()
        usuario.nome           = usuarioVO.nome
        usuario.usuario        = usuarioVO.usuario
        usuario.dataNascimento = usuarioVO.dataNascimento
        usuario.senha          = usuarioVO.senha
        usuario.telefone1      = usuarioVO.telefone1
        usuario.telefone2      = usuarioVO.telefone2
        usuario.usuarioWeb     = usuarioVO.usuarioWeb
        usuario.email          = usuarioVO.email
        usuario.ativo          = usuarioVO.ativo
        usuario.cor            = usuarioVO.cor
        usuario.criado         = Calendar.getInstance().formataParaBrasileiro()

        return usuarioRepository.save(usuario)
    }

    fun atualizar(usuarioVO: UsuarioVO,
                  id: Long): Usuario {

        if (usuarioVO.perfil.toInt() == 0 )
            throw Exception("Campo perfil é obrigatório")

        val usuario = buscarPorId(id) ?: throw Exception("Usuario não encontrado para atualização")
        usuario.nome           = usuarioVO.nome
        usuario.usuario        = usuarioVO.usuario
        usuario.dataNascimento = usuarioVO.dataNascimento
        usuario.senha          = usuarioVO.senha
        usuario.telefone1      = usuarioVO.telefone1
        usuario.telefone2      = usuarioVO.telefone2
        usuario.usuarioWeb     = usuarioVO.usuarioWeb
        usuario.email          = usuarioVO.email
        usuario.ativo          = usuarioVO.ativo
        usuario.cor            = usuarioVO.cor
        usuario.atualizado     = Calendar.getInstance().formataParaBrasileiro()

        return usuarioRepository.save(usuario)
    }

    fun deletar(id: Long) {
        val usuario = buscarPorId(id) ?: throw Exception("Usuario não encontrado para Exclusão")
        usuario.excluido = Calendar.getInstance().formataParaBrasileiro()
        usuarioRepository.save(usuario)
    }
}
