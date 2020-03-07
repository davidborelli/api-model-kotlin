package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Extrato
import br.com.gimb.api.model.vo.ExtratoVO
import br.com.gimb.api.repository.ExtratoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExtratoService {

    @Autowired
    lateinit var extratoRepository: ExtratoRepository

    fun buscarTodos(): MutableList<Extrato>{
        return extratoRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): Extrato? {
        val optional = extratoRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()

        return null
    }

    fun salvar(extratoVO: ExtratoVO): Extrato {

        if (extratoVO.despesa.toInt() == 0 || extratoVO.tipoPagamento.toInt() == 0 || extratoVO.usuarioCriou.toInt() == 0
                || extratoVO.usuarioAprovou.toInt() == 0 || extratoVO.usuario.toInt() == 0 ) {
            throw Exception("Campos obrigatórios o preenchimento: Despesa/TipoPagamento/UsuarioCriou/UsuarioAprovou/Usuario")
        }

        val extrato = Extrato()
        extrato.dataLiberacao     = extratoVO.dataLiberacao
        extrato.tipo              = extratoVO.tipo
        extrato.valor             = extratoVO.valor
        extrato.despesa.id        = extratoVO.despesa
        extrato.tipoPagamento.id  = extratoVO.tipoPagamento
        extrato.statusPagamento   = extratoVO.statusPagamento
        extrato.dataCriacao       = extratoVO.dataCriacao
        extrato.usuarioCriou.id   = extratoVO.usuarioCriou
        extrato.data_aprovacao    = extratoVO.data_aprovacao
        extrato.usuarioAprovou.id = extratoVO.usuarioAprovou
        extrato.usuario.id        = extratoVO.usuario
        extrato.informacao        = extratoVO.informacao
        extrato.observacao        = extratoVO.observacao
        extrato.criado            = Calendar.getInstance().formataParaBrasileiro()

        return extratoRepository.save(extrato)
    }

    fun atualizar(extratoVO: ExtratoVO,
                  id: Long): Extrato {

        if (extratoVO.despesa.toInt() == 0 || extratoVO.tipoPagamento.toInt() == 0 || extratoVO.usuarioCriou.toInt() == 0
                || extratoVO.usuarioAprovou.toInt() == 0 || extratoVO.usuario.toInt() == 0 ) {
            throw Exception("Campos obrigatórios o preenchimento: Despesa/TipoPagamento/UsuarioCriou/UsuarioAprovou/Usuario")
        }

        val extrato = buscarPorId(id) ?: throw Exception("Extrato não encontrado para atualização")
        extrato.dataLiberacao     = extratoVO.dataLiberacao
        extrato.tipo              = extratoVO.tipo
        extrato.valor             = extratoVO.valor
        extrato.despesa.id        = extratoVO.despesa
        extrato.tipoPagamento.id  = extratoVO.tipoPagamento
        extrato.statusPagamento   = extratoVO.statusPagamento
        extrato.dataCriacao       = extratoVO.dataCriacao
        extrato.usuarioCriou.id   = extratoVO.usuarioCriou
        extrato.data_aprovacao    = extratoVO.data_aprovacao
        extrato.usuarioAprovou.id = extratoVO.usuarioAprovou
        extrato.usuario.id        = extratoVO.usuario
        extrato.informacao        = extratoVO.informacao
        extrato.observacao        = extratoVO.observacao
        extrato.atualizado        = Calendar.getInstance().formataParaBrasileiro()

        return extratoRepository.save(extrato)
    }

    fun deletar(id: Long) {
        val extrato = buscarPorId(id) ?: throw Exception("Extrato não encontrado para Exclusão")
        extrato.excluido = Calendar.getInstance().formataParaBrasileiro()
        extratoRepository.save(extrato)
    }
}
