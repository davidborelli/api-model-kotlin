package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.ApontamentoEvento
import br.com.gimb.api.model.vo.ApontamentoEventoVO
import br.com.gimb.api.repository.ApontamentoEventoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ApontamentoEventoService {

    @Autowired
    lateinit var apontamentoEventoRepository: ApontamentoEventoRepository

    fun buscarTodos(): MutableList<ApontamentoEvento> {
        return apontamentoEventoRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): ApontamentoEvento? {
        val optional = apontamentoEventoRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(apontamentoEventoVo: ApontamentoEventoVO): ApontamentoEvento {
        if (apontamentoEventoVo.evento.toInt() == 0 || apontamentoEventoVo.apontamento.toInt() == 0){
            throw Exception("Apontamento e/ou Evento precisam ser informados")
        }

        val apontamentoEvento = ApontamentoEvento()

        apontamentoEvento.dataHora             = apontamentoEventoVo.dataHora
        apontamentoEvento.latitude             = apontamentoEventoVo.latitude
        apontamentoEvento.longitude            = apontamentoEventoVo.longitude
        apontamentoEvento.apontamento.id       = apontamentoEventoVo.apontamento
        apontamentoEvento.evento.id            = apontamentoEventoVo.evento
        apontamentoEvento.tempoInicial         = apontamentoEventoVo.tempoInicial
        apontamentoEvento.tempoFinal           = apontamentoEventoVo.tempoFinal
        apontamentoEvento.tempoInicialOriginal = apontamentoEventoVo.tempoInicialOriginal
        apontamentoEvento.tempoFinalOriginal   = apontamentoEventoVo.tempoFinalOriginal
        apontamentoEvento.observacao           = apontamentoEventoVo.observacao
        apontamentoEvento.criado               = Calendar.getInstance().formataParaBrasileiro()

        return apontamentoEventoRepository.save(apontamentoEvento)
    }

    fun atualizar(apontamentoEventoVo: ApontamentoEventoVO,
                  id: Long): ApontamentoEvento {

        if (apontamentoEventoVo.evento.toInt() == 0 || apontamentoEventoVo.apontamento.toInt() == 0){
            throw Exception("Apontamento e/ou Evento precisam ser informados")
        }

        val apontamentoEvento = buscarPorId(id) ?: throw Exception("Apontamento Evento não encontrado para atualização")

        apontamentoEvento.dataHora             = apontamentoEventoVo.dataHora
        apontamentoEvento.latitude             = apontamentoEventoVo.latitude
        apontamentoEvento.longitude            = apontamentoEventoVo.longitude
        apontamentoEvento.apontamento.id       = apontamentoEventoVo.apontamento
        apontamentoEvento.evento.id            = apontamentoEventoVo.evento
        apontamentoEvento.tempoInicial         = apontamentoEventoVo.tempoInicial
        apontamentoEvento.tempoFinal           = apontamentoEventoVo.tempoFinal
        apontamentoEvento.tempoInicialOriginal = apontamentoEventoVo.tempoInicialOriginal
        apontamentoEvento.tempoFinalOriginal   = apontamentoEventoVo.tempoFinalOriginal
        apontamentoEvento.observacao           = apontamentoEventoVo.observacao
        apontamentoEvento.atualizado           = Calendar.getInstance().formataParaBrasileiro()

        return apontamentoEventoRepository.save(apontamentoEvento)
    }

    fun deletarPorId(id: Long) {
        val apontamentoEvento =  buscarPorId(id) ?: throw Exception("Apontamento Evento não encontrado para Excluir")
        apontamentoEvento.excluido = Calendar.getInstance().formataParaBrasileiro()
        apontamentoEventoRepository.save(apontamentoEvento)
    }
}
