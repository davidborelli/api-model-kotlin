package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Observacao
import br.com.gimb.api.model.vo.ObservacaoVO
import br.com.gimb.api.repository.ObservacaoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ObservacaoService {

    @Autowired
    lateinit var observacaoRepository: ObservacaoRepository

    fun buscarTodos(ativo: Boolean?): MutableList<Observacao> {
        return when (ativo) {
            null -> observacaoRepository.findAllByExcluidoNull()
            else -> observacaoRepository.findByAtivoAndExcluidoNull(ativo)
        }
    }

    fun buscarPorId(id: Long): Observacao? {
        val optional = observacaoRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(observacaoVO: ObservacaoVO): Observacao {

        val observacao = Observacao()
        observacao.ativo      = observacaoVO.ativo
        observacao.inventario = observacaoVO.inventario
        observacao.nome       = observacaoVO.nome
        observacao.criado     = Calendar.getInstance().formataParaBrasileiro()

        return observacaoRepository.save(observacao)
    }

    fun atualizar(observacaoVO: ObservacaoVO,
                  id: Long): Observacao {

        val observacao = buscarPorId(id) ?: throw Exception("Observação não encontrado para atualização")
        observacao.ativo      = observacaoVO.ativo
        observacao.inventario = observacaoVO.inventario
        observacao.nome       = observacaoVO.nome
        observacao.atualizado = Calendar.getInstance().formataParaBrasileiro()

        return observacaoRepository.save(observacao)
    }

    fun deletar(id: Long) {
        val observacao = buscarPorId(id) ?: throw Exception("Observação não encontrado para Exclusão")
        observacao.excluido = Calendar.getInstance().formataParaBrasileiro()
        observacaoRepository.save(observacao)
    }


}
