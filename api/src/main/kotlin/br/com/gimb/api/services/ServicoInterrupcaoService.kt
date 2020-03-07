package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.ServicoInterrupcao
import br.com.gimb.api.model.vo.ServicoInterrupcaoVO
import br.com.gimb.api.repository.ServicoInterrupcaoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ServicoInterrupcaoService {

    @Autowired
    lateinit var servicoInterrupcaoRepository: ServicoInterrupcaoRepository

    fun buscarTodos(): MutableList<ServicoInterrupcao> {
        return servicoInterrupcaoRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): ServicoInterrupcao? {
        val optional = servicoInterrupcaoRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(servicoInterrupcaoVO: ServicoInterrupcaoVO): ServicoInterrupcao {

        if (servicoInterrupcaoVO.servico.toInt() == 0) {
            throw Exception("Campo Servico obrigatorio informar")
        }

        val servicoInterrupcao = ServicoInterrupcao()
        servicoInterrupcao.tempoInicial    = servicoInterrupcaoVO.tempoInicial
        servicoInterrupcao.tempoFinal      = servicoInterrupcaoVO.tempoFinal
        servicoInterrupcao.tempoInicialLat = servicoInterrupcaoVO.tempoInicialLat
        servicoInterrupcao.tempoFinalLat   = servicoInterrupcaoVO.tempoFinalLat
        servicoInterrupcao.tempoInicialLgt = servicoInterrupcaoVO.tempoInicialLgt
        servicoInterrupcao.tempoFinalLgt   = servicoInterrupcaoVO.tempoFinalLgt
        servicoInterrupcao.servico.id      = servicoInterrupcaoVO.servico
        servicoInterrupcao.criado          = Calendar.getInstance().formataParaBrasileiro()

        return servicoInterrupcaoRepository.save(servicoInterrupcao)
    }

    fun atualizar(servicoInterrupcaoVO: ServicoInterrupcaoVO,
                  id: Long): ServicoInterrupcao {

        if (servicoInterrupcaoVO.servico.toInt() == 0) {
            throw Exception("Campo Servico obrigatorio informar")
        }

        val servicoInterrupcao = buscarPorId(id) ?: throw Exception("ServicoInterrupcao não encontrada para atualização")
        servicoInterrupcao.tempoInicial    = servicoInterrupcaoVO.tempoInicial
        servicoInterrupcao.tempoFinal      = servicoInterrupcaoVO.tempoFinal
        servicoInterrupcao.tempoInicialLat = servicoInterrupcaoVO.tempoInicialLat
        servicoInterrupcao.tempoFinalLat   = servicoInterrupcaoVO.tempoFinalLat
        servicoInterrupcao.tempoInicialLgt = servicoInterrupcaoVO.tempoInicialLgt
        servicoInterrupcao.tempoFinalLgt   = servicoInterrupcaoVO.tempoFinalLgt
        servicoInterrupcao.servico.id      = servicoInterrupcaoVO.servico
        servicoInterrupcao.atualizado      = Calendar.getInstance().formataParaBrasileiro()

        return servicoInterrupcaoRepository.save(servicoInterrupcao)
    }

    fun deletar(id: Long) {
        val servicoInterrupcao = buscarPorId(id) ?: throw Exception("ServicoInterrupcao não encontrada para Exclusão")
        servicoInterrupcao.excluido = Calendar.getInstance().formataParaBrasileiro()
        servicoInterrupcaoRepository.save(servicoInterrupcao)
    }
}
