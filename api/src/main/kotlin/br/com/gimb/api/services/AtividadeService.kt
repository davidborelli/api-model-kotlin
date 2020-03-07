package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Atividade
import br.com.gimb.api.model.vo.AtividadeVO
import br.com.gimb.api.repository.AtividadeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AtividadeService {

    @Autowired
    lateinit var atividadeRepository: AtividadeRepository

    fun buscarTodos(ativo: Boolean?): MutableList<Atividade> {
        return when (ativo) {
            null -> atividadeRepository.findAllByExcluidoNull()
            else -> atividadeRepository.findByAtivoAndExcluidoNull(ativo)
        }
    }

    fun buscarPorId(id: Long): Atividade? {
        val optional = atividadeRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(atividadeVo: AtividadeVO) : Atividade {
        val atividade = Atividade()

        atividade.ativo            = atividadeVo.ativo
        atividade.cor              = atividadeVo.cor
        atividade.nome             = atividadeVo.nome
        atividade.tempoImprodutivo = atividadeVo.tempoImprodutivo
        atividade.operacional      = atividadeVo.operacional
        atividade.financeiro       = atividadeVo.financeiro
        atividade.apontamento      = atividadeVo.apontamento
        atividade.checklist        = atividadeVo.checklist
        atividade.solicitarKm      = atividadeVo.solicitarKm

        atividade.criado = Calendar.getInstance().formataParaBrasileiro()

        return atividadeRepository.save(atividade)
    }

    fun atualizar(atividadeVo: AtividadeVO,
                  id: Long) : Atividade {

        val atividade = buscarPorId(id) ?: throw Exception("Atividade não encontrado para atualização")

        atividade.ativo      = atividadeVo.ativo
        atividade.cor        = atividadeVo.cor
        atividade.nome       = atividadeVo.nome
        atividade.atualizado = Calendar.getInstance().formataParaBrasileiro()

        return atividadeRepository.save(atividade)
    }

    fun deletarPorId(id: Long) {
        val atividade = buscarPorId(id) ?: throw Exception("Atividade não encontrado para Exclusão")
        atividade.excluido = Calendar.getInstance().formataParaBrasileiro()
        atividadeRepository.save(atividade)
    }
}
