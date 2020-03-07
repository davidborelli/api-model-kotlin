package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Apontamento
import br.com.gimb.api.model.vo.ApontamentoVO
import br.com.gimb.api.repository.ApontamentoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ApontamentoService {

    @Autowired
    lateinit var apontamentoRepository: ApontamentoRepository

    fun buscarTodos(): MutableList<Apontamento> {
        return apontamentoRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): Apontamento? {
        val optional = apontamentoRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(apontamentoVo: ApontamentoVO): Apontamento {
        if (apontamentoVo.cliente.toInt() == 0 || apontamentoVo.usuario.toInt() == 0){
            throw Exception("Cliente e/ou Usuário precisam ser informados")
        }

        val apontamento = Apontamento()
        apontamento.tempoInicial         = apontamentoVo.tempoInicial
        apontamento.tempoFinal           = apontamentoVo.tempoFinal
        apontamento.tempoInicialLat      = apontamentoVo.tempoInicialLat
        apontamento.tempoInicialLgt      = apontamentoVo.tempoInicialLgt
        apontamento.tempoFinalLat        = apontamentoVo.tempoFinalLat
        apontamento.tempoFinalLgt        = apontamentoVo.tempoFinalLgt
        apontamento.status               = apontamentoVo.status
        apontamento.cliente.id           = apontamentoVo.cliente
        apontamento.usuario.id           = apontamentoVo.cliente
        apontamento.tempoInicialOriginal = apontamentoVo.tempoInicialOriginal
        apontamento.tempoFinalOriginal   = apontamentoVo.tempoFinalOriginal
        apontamento.anotacao             = apontamentoVo.anotacao
        apontamento.criado               = Calendar.getInstance().formataParaBrasileiro()


        return apontamentoRepository.save(apontamento)
    }

    fun atualizar(apontamentoVo: ApontamentoVO,
                  id: Long): Apontamento {

        if (apontamentoVo.cliente.toInt() == 0 || apontamentoVo.usuario.toInt() == 0){
            throw Exception("Cliente e/ou Usuário precisam ser informados")
        }

        val apontamento = buscarPorId(id) ?: throw Exception("Apontamento não encontrado para atualização")
        apontamento.tempoInicial         = apontamentoVo.tempoInicial
        apontamento.tempoFinal           = apontamentoVo.tempoFinal
        apontamento.tempoInicialLat      = apontamentoVo.tempoInicialLat
        apontamento.tempoInicialLgt      = apontamentoVo.tempoInicialLgt
        apontamento.tempoFinalLat        = apontamentoVo.tempoFinalLat
        apontamento.tempoFinalLgt        = apontamentoVo.tempoFinalLgt
        apontamento.status               = apontamentoVo.status
        apontamento.cliente.id           = apontamentoVo.cliente
        apontamento.usuario.id           = apontamentoVo.cliente
        apontamento.tempoInicialOriginal = apontamentoVo.tempoInicialOriginal
        apontamento.tempoFinalOriginal   = apontamentoVo.tempoFinalOriginal
        apontamento.anotacao             = apontamentoVo.anotacao


        return apontamentoRepository.save(apontamento)
    }

    fun deletarPorId(id: Long) {
        val apontamento = buscarPorId(id) ?: throw Exception("Apontamento não encontrado para Exclusão")
        apontamento.excluido = Calendar.getInstance().formataParaBrasileiro()
        apontamentoRepository.save(apontamento)
    }
}
