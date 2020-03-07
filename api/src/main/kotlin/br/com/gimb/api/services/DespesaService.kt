package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Despesa
import br.com.gimb.api.model.vo.DespesaVO
import br.com.gimb.api.repository.DespesaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class DespesaService {

    @Autowired
    lateinit var despesaRepository: DespesaRepository

    fun buscarTodos(): MutableList<Despesa> {
        return despesaRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): Despesa? {
        val optional = despesaRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(despesaVO: DespesaVO): Despesa {

        if (despesaVO.cliente.toInt() == 0 ||  despesaVO.evento.toInt() == 0 ||
            despesaVO.usuario.toInt() == 0 ||  despesaVO.tipoPagamento.toInt() == 0) {
            throw Exception("Campos que precisam ser informados: Cliente/Usuário/Evento/TipoPagamento")
        }

        val despesa = Despesa()
        despesa.valor             = despesaVO.valor
        despesa.data              = despesaVO.data
        despesa.latitude          = despesaVO.latitude
        despesa.longitude         = despesaVO.longitude
        despesa.anotacao          = despesaVO.anotacao
        despesa.numFormaPagamento = despesaVO.numFormaPagamento
        despesa.cliente.id        = despesaVO.cliente
        despesa.evento.id         = despesaVO.evento
        despesa.usuario.id        = despesaVO.usuario
        despesa.tipoPagamento.id  = despesaVO.tipoPagamento
        despesa.criado            = Calendar.getInstance().formataParaBrasileiro()

        return despesaRepository.save(despesa)
    }

    fun atualizar(despesaVO: DespesaVO,
                  id: Long): Despesa {

        if (despesaVO.cliente.toInt() == 0 ||  despesaVO.evento.toInt()        == 0 ||
                despesaVO.usuario.toInt() == 0 ||  despesaVO.tipoPagamento.toInt() == 0) {
            throw Exception("Campos que precisam ser informados: Cliente/Usuário/Evento/TipoPagamento")
        }

        val despesa = buscarPorId(id) ?: throw Exception("Despesa não encontrado para atualização")
        despesa.valor             = despesaVO.valor
        despesa.data              = despesaVO.data
        despesa.latitude          = despesaVO.latitude
        despesa.longitude         = despesaVO.longitude
        despesa.anotacao          = despesaVO.anotacao
        despesa.numFormaPagamento = despesaVO.numFormaPagamento
        despesa.cliente.id        = despesaVO.cliente
        despesa.evento.id         = despesaVO.evento
        despesa.usuario.id        = despesaVO.usuario
        despesa.tipoPagamento.id  = despesaVO.tipoPagamento
        despesa.atualizado        = Calendar.getInstance().formataParaBrasileiro()

        return despesaRepository.save(despesa)
    }

    fun deletar(id: Long) {
        val despesa = buscarPorId(id) ?: throw Exception("Despesa não encontrado para Exclusão")
        despesa.excluido = Calendar.getInstance().formataParaBrasileiro()
        despesaRepository.save(despesa)
    }

}
