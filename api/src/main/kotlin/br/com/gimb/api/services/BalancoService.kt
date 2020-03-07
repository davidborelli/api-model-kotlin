package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Balanco
import br.com.gimb.api.model.vo.BalancoVO
import br.com.gimb.api.repository.BalancoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BalancoService {

    @Autowired
    lateinit var balancoRepository: BalancoRepository

    fun buscarTodos(): MutableList<Balanco> {
        return balancoRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): Balanco? {
        val optional = balancoRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(balancoVo: BalancoVO): Balanco {
        if (balancoVo.usuario.toInt() == 0 || balancoVo.tipo_pagamento.toInt() == 0){
            throw Exception("Usuário e/ou Tipo Pagamento precisam ser informados")
        }

        val balanco = Balanco()

        balanco.data              = balancoVo.data
        balanco.tipoPagamento.id  = balancoVo.tipo_pagamento
        balanco.usuario.id        = balancoVo.usuario
        balanco.valor             = balancoVo.valor
        balanco.criado            = Calendar.getInstance().formataParaBrasileiro()

        return balancoRepository.save(balanco)
    }

    fun atualizar(balancoVo: BalancoVO,
                  id: Long): Balanco {

        if (balancoVo.usuario.toInt() == 0 || balancoVo.tipo_pagamento.toInt() == 0){
            throw Exception("Usuário e/ou Tipo Pagamento precisam ser informados")
        }

        val balanco = buscarPorId(id) ?: throw Exception("Balanço não encontrado para atualização")

        balanco.data              = balancoVo.data
        balanco.tipoPagamento.id = balancoVo.tipo_pagamento
        balanco.usuario.id        = balancoVo.usuario
        balanco.valor             = balancoVo.valor
        balanco.atualizado        = Calendar.getInstance().formataParaBrasileiro()

        return balancoRepository.save(balanco)
    }

    fun deletarPorId(id: Long) {
        val balanco = buscarPorId(id) ?: throw Exception("Balanço não encontrado para Exclusão")
        balanco.excluido = Calendar.getInstance().formataParaBrasileiro()
        balancoRepository.save(balanco)
    }
}
