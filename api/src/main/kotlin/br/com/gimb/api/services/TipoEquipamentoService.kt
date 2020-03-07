package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.TipoEquipamento
import br.com.gimb.api.model.vo.TipoEquipamentoVO
import br.com.gimb.api.repository.TipoEquipamentoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TipoEquipamentoService {

    @Autowired
    lateinit var tipoEquipamentoRepository: TipoEquipamentoRepository

    fun buscarTodos(ativo: Boolean): MutableList<TipoEquipamento> {
        return when (ativo){
            null -> tipoEquipamentoRepository.findAllByExcluidoNull()
            else -> tipoEquipamentoRepository.findByAtivoAndExcluidoNull(ativo)
        }
    }

    fun buscarPorId(id: Long): TipoEquipamento? {
        val optional = tipoEquipamentoRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(tipoEquipamentoVO: TipoEquipamentoVO): TipoEquipamento {

        val tipoEquipamento = TipoEquipamento()
        tipoEquipamento.ativo         = tipoEquipamentoVO.ativo
        tipoEquipamento.nome          = tipoEquipamentoVO.nome
        tipoEquipamento.tempoEstimado = tipoEquipamentoVO.tempoEstimado
        tipoEquipamento.criado        = Calendar.getInstance().formataParaBrasileiro()

        return tipoEquipamentoRepository.save(tipoEquipamento)
    }

    fun atualizar(tipoEquipamentoVO: TipoEquipamentoVO,
                  id: Long): TipoEquipamento {

        val tipoEquipamento = buscarPorId(id) ?: throw Exception("TipoEquipamento não encontrado para atualização")
        tipoEquipamento.ativo         = tipoEquipamentoVO.ativo
        tipoEquipamento.nome          = tipoEquipamentoVO.nome
        tipoEquipamento.tempoEstimado = tipoEquipamentoVO.tempoEstimado
        tipoEquipamento.atualizado    = Calendar.getInstance().formataParaBrasileiro()

        return tipoEquipamentoRepository.save(tipoEquipamento)
    }

    fun deletar(id: Long) {
        val tipoEquipamento = buscarPorId(id) ?: throw Exception("TipoEquipamento não encontrado para Exclusão")
        tipoEquipamento.excluido = Calendar.getInstance().formataParaBrasileiro()
        tipoEquipamentoRepository.save(tipoEquipamento)
    }
}
