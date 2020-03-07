package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.TipoPagamento
import br.com.gimb.api.model.vo.TipoPagamentoVO
import br.com.gimb.api.repository.TipoPagamentoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TipoPagamentoService {

    @Autowired
    lateinit var tipoPagamentoRepository: TipoPagamentoRepository

    fun buscarTodos(ativo: Boolean): MutableList<TipoPagamento> {
        return when (ativo){
            null -> tipoPagamentoRepository.findAllByExcluidoNull()
            else -> tipoPagamentoRepository.findByAtivoAndExcluidoNull(ativo)
        }
    }

    fun buscarPorId(id: Long): TipoPagamento? {
        val optional = tipoPagamentoRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(tipoPagamentoVO: TipoPagamentoVO): TipoPagamento {

        val tipoPagamento = TipoPagamento()
        tipoPagamento.nome                  = tipoPagamentoVO.nome
        tipoPagamento.consomeSaldo          = tipoPagamentoVO.consomeSaldo
        tipoPagamento.solicitaIdentificador = tipoPagamentoVO.solicitaIdentificador
        tipoPagamento.ativo                 = tipoPagamentoVO.ativo
        tipoPagamento.cor                   = tipoPagamentoVO.cor
        tipoPagamento.criado                = Calendar.getInstance().formataParaBrasileiro()

        return tipoPagamentoRepository.save(tipoPagamento)
    }

    fun atualizar(tipoPagamentoVO: TipoPagamentoVO,
                  id: Long): TipoPagamento {

        val tipoPagamento = buscarPorId(id) ?: throw Exception("TipoPagamento não encontrado para atualização")
        tipoPagamento.nome                  = tipoPagamentoVO.nome
        tipoPagamento.consomeSaldo          = tipoPagamentoVO.consomeSaldo
        tipoPagamento.solicitaIdentificador = tipoPagamentoVO.solicitaIdentificador
        tipoPagamento.ativo                 = tipoPagamentoVO.ativo
        tipoPagamento.cor                   = tipoPagamentoVO.cor
        tipoPagamento.atualizado            = Calendar.getInstance().formataParaBrasileiro()

        return tipoPagamentoRepository.save(tipoPagamento)
    }

    fun deletar(id: Long) {
        val tipoPagamento = buscarPorId(id) ?: throw Exception("TipoPagamento não encontrado para Exclusão")
        tipoPagamento.excluido = Calendar.getInstance().formataParaBrasileiro()
        tipoPagamentoRepository.save(tipoPagamento)
    }
}
