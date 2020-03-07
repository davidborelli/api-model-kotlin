package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.CampoDescricoes
import br.com.gimb.api.model.vo.CampoDescricoesVO
import br.com.gimb.api.repository.CampoDescricoesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CampoDescricoesService {

    @Autowired
    lateinit var campoDescricoesRepository: CampoDescricoesRepository

    fun buscarTodos(): MutableList<CampoDescricoes> {
        return campoDescricoesRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): CampoDescricoes? {
        val optional = campoDescricoesRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(campoDescricoesVo: CampoDescricoesVO): CampoDescricoes {
        val campoDescricoes = CampoDescricoes()

        campoDescricoes.descricao    = campoDescricoesVo.descricao
        campoDescricoes.obrigatorio  = campoDescricoesVo.obrigatorio
        campoDescricoes.referencia   = campoDescricoesVo.referencia
        campoDescricoes.tipo         = campoDescricoesVo.tipo
        campoDescricoes.valorDefault = campoDescricoesVo.valorDefault
        campoDescricoes.criado       = Calendar.getInstance().formataParaBrasileiro()

        return campoDescricoesRepository.save(campoDescricoes)
    }

    fun atualizar(campoDescricoesVo: CampoDescricoesVO,
                  id: Long): CampoDescricoes {

        val campoDescricoes = buscarPorId(id) ?: throw Exception("Campo Descrição não encontrado para atualização")

        campoDescricoes.descricao    = campoDescricoesVo.descricao
        campoDescricoes.obrigatorio  = campoDescricoesVo.obrigatorio
        campoDescricoes.referencia   = campoDescricoesVo.referencia
        campoDescricoes.tipo         = campoDescricoesVo.tipo
        campoDescricoes.valorDefault = campoDescricoesVo.valorDefault
        campoDescricoes.atualizado   = Calendar.getInstance().formataParaBrasileiro()

        return campoDescricoesRepository.save(campoDescricoes)
    }

    fun deletar(id: Long) {
        val campoDescricoes = buscarPorId(id) ?: throw Exception("Campo Descrição não encontrado para Exclusão")
        campoDescricoes.excluido = Calendar.getInstance().formataParaBrasileiro()
        campoDescricoesRepository.save(campoDescricoes)
    }


}
