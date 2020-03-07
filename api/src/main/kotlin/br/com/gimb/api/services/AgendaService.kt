package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Agenda
import br.com.gimb.api.model.AgendaVO
import br.com.gimb.api.repository.AgendaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AgendaService {

    @Autowired
    lateinit var agendaRepository: AgendaRepository

    fun buscarTodos(): MutableList<Agenda> {
        return agendaRepository.findAll() as MutableList<Agenda>
    }

    fun salvar(agendaVO: AgendaVO): Agenda {
        val agenda = Agenda()
//        agenda.guid         = UUID.randomUUID().toString()
        agenda.guid         = agendaVO.guid
        agenda.data         = agendaVO.data
        agenda.usuario.guid = agendaVO.usuario.guid
        agenda.criado       = Calendar.getInstance().formataParaBrasileiro()

        return agendaRepository.save(agenda)
    }

    fun atualizar(agendaVO: AgendaVO,
                  guid: String): Agenda {

        val agenda = buscarPorGuid(guid) ?: throw Exception("Agenda não encontrado para atualização")

        agenda.data       = agenda.data
        agenda.usuario    = agenda.usuario
        agenda.atualizado = Calendar.getInstance().formataParaBrasileiro()

        return agendaRepository.save(agenda)
    }

    fun buscarPorGuid(guid: String): Agenda? {
        val optional = agendaRepository.findByGuid(guid)
        if (optional.isPresent){
            return optional.get()
        }
        return null
    }

    fun deletarPorGuid(guid: String) {
        val agendaLocalizado =  buscarPorGuid(guid) ?: throw Exception("Agenda não encontrado para Excluir")
        agendaLocalizado.excluido = Calendar.getInstance().formataParaBrasileiro()
        agendaRepository.delete(agendaLocalizado)
    }
}
