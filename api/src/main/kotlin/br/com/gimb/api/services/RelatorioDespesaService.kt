package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.RelatorioDespesa
import br.com.gimb.api.model.vo.RelatorioDespesaVO
import br.com.gimb.api.repository.RelatorioDespesaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class RelatorioDespesaService {

    @Autowired
    lateinit var relatorioDespesaRepository: RelatorioDespesaRepository

    fun buscarTodos(): MutableList<RelatorioDespesa>{
        return relatorioDespesaRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): RelatorioDespesa? {
        val optional = relatorioDespesaRepository.findByIdAndExcluidoNull(id)
        if ( optional.isPresent )
            return optional.get()
        return null
    }

    fun salvar(relatorioDespesaVO: RelatorioDespesaVO): RelatorioDespesa {

        val relatorioDespesa = RelatorioDespesa()
        relatorioDespesa.dataCriacao            = relatorioDespesaVO.dataCriacao
        relatorioDespesa.dataFinalizacao        = relatorioDespesaVO.dataFinalizacao
        relatorioDespesa.anotacaoDespesa        = relatorioDespesaVO.anotacaoDespesa
        relatorioDespesa.dataExpiracaoPagamento = relatorioDespesaVO.dataExpiracaoPagamento
        relatorioDespesa.anotacaoPagamento      = relatorioDespesaVO.anotacaoPagamento
        relatorioDespesa.status                 = relatorioDespesaVO.status
        relatorioDespesa.hash                   = relatorioDespesaVO.hash
        relatorioDespesa.clienteId              = relatorioDespesaVO.clienteId
        relatorioDespesa.usuarioId              = relatorioDespesaVO.usuarioId
        relatorioDespesa.criado                 = Calendar.getInstance().formataParaBrasileiro()

        return relatorioDespesaRepository.save(relatorioDespesa)
    }

    fun atualizar(relatorioDespesaVO: RelatorioDespesaVO,
                  id: Long): RelatorioDespesa {

        val relatorioDespesa = buscarPorId(id) ?: throw Exception("Relatorio Despesa não encontrado para atualização")
        relatorioDespesa.dataCriacao            = relatorioDespesaVO.dataCriacao
        relatorioDespesa.dataFinalizacao        = relatorioDespesaVO.dataFinalizacao
        relatorioDespesa.anotacaoDespesa        = relatorioDespesaVO.anotacaoDespesa
        relatorioDespesa.dataExpiracaoPagamento = relatorioDespesaVO.dataExpiracaoPagamento
        relatorioDespesa.anotacaoPagamento      = relatorioDespesaVO.anotacaoPagamento
        relatorioDespesa.status                 = relatorioDespesaVO.status
        relatorioDespesa.hash                   = relatorioDespesaVO.hash
        relatorioDespesa.clienteId              = relatorioDespesaVO.clienteId
        relatorioDespesa.usuarioId              = relatorioDespesaVO.usuarioId
        relatorioDespesa.atualizado             = Calendar.getInstance().formataParaBrasileiro()

        return relatorioDespesaRepository.save(relatorioDespesa)
    }

    fun deletar(id: Long) {
        val relatorioDespesa = buscarPorId(id) ?: throw Exception("Relatorio Despesa não encontrado para Exclusão")
        relatorioDespesa.excluido = Calendar.getInstance().formataParaBrasileiro()
        relatorioDespesaRepository.save(relatorioDespesa)
    }
}
