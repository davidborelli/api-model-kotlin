package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Perfil
import br.com.gimb.api.model.vo.PerfilVO
import br.com.gimb.api.repository.PerfilRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PerfilService {

    @Autowired
    lateinit var perfilRepository: PerfilRepository

    fun buscarTodos(ativo: Boolean?): MutableList<Perfil> {
        return when (ativo) {
            null -> perfilRepository.findAllByExcluidoNull()
            else -> perfilRepository.findByAtivoAndExcluidoNull(ativo)
        }
    }

    fun buscarPorId(id: Long): Perfil? {
        val optional = perfilRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(perfilVO: PerfilVO): Perfil {

        val perfil = Perfil()
        perfil.nome            = perfilVO.nome
        perfil.ativo           = perfilVO.ativo
        perfil.acessoWeb       = perfilVO.acessoWeb
        perfil.acessoApp       = perfilVO.acessoApp
        perfil.configuracaoWeb = perfilVO.configuracaoWeb
        perfil.configuracaoApp = perfilVO.configuracaoApp
        perfil.cor             = perfilVO.cor
        perfil.criado          = Calendar.getInstance().formataParaBrasileiro()

        return perfilRepository.save(perfil)
    }

    fun atualizar(perfilVO: PerfilVO,
                  id: Long): Perfil {

        val perfil = buscarPorId(id) ?: throw Exception("Perfil não encontrado para atualização")
        perfil.nome            = perfilVO.nome
        perfil.ativo           = perfilVO.ativo
        perfil.acessoWeb       = perfilVO.acessoWeb
        perfil.acessoApp       = perfilVO.acessoApp
        perfil.configuracaoWeb = perfilVO.configuracaoWeb
        perfil.configuracaoApp = perfilVO.configuracaoApp
        perfil.cor             = perfilVO.cor
        perfil.atualizado      = Calendar.getInstance().formataParaBrasileiro()

        return perfilRepository.save(perfil)
    }

    fun deletar(id: Long) {
        val perfil = buscarPorId(id) ?: throw Exception("Perfil não encontrado para Exclusão")
        perfil.excluido = Calendar.getInstance().formataParaBrasileiro()
        perfilRepository.save(perfil)
    }

}
