package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Evento
import br.com.gimb.api.model.vo.EventoVO
import br.com.gimb.api.repository.EventoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class EventoService {

    @Autowired
    lateinit var eventoRepository: EventoRepository

    fun buscarTodos(ativo: Boolean?): MutableList<Evento> {
        return when (ativo) {
            null -> eventoRepository.findAllByExcluidoNull()
            else -> eventoRepository.findByAtivoAndExcluidoNull(ativo)
        }
    }

    fun buscarPorId(id: Long): Evento? {
        val optional = eventoRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(eventoVo: EventoVO): Evento {

        val evento = Evento()
        evento.nome             = eventoVo.nome
        evento.tempoImprodutivo = eventoVo.tempoImprodutivo
        evento.os               = eventoVo.os
        evento.liquidacao       = eventoVo.liquidacao
        evento.ativo            = eventoVo.ativo
        evento.criado           = Calendar.getInstance().formataParaBrasileiro()

        return eventoRepository.save(evento)
    }

    fun atualizar(eventoVo: EventoVO,
                  id: Long): Evento {

        val evento = buscarPorId(id) ?: throw Exception("Evento não encontrado para atualização")
        evento.nome             = eventoVo.nome
        evento.tempoImprodutivo = eventoVo.tempoImprodutivo
        evento.os               = eventoVo.os
        evento.liquidacao       = eventoVo.liquidacao
        evento.ativo            = eventoVo.ativo
        evento.atualizado       = Calendar.getInstance().formataParaBrasileiro()

        return eventoRepository.save(evento)
    }

    fun deletarPorId(id: Long) {
        val evento = buscarPorId(id) ?: throw Exception("Evento não encontrado para Exclusão")
        evento.excluido = Calendar.getInstance().formataParaBrasileiro()
        eventoRepository.save(evento)
    }


}
