package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.AgendaItens
import br.com.gimb.api.model.AgendaItensVO
import br.com.gimb.api.repository.AgendaItensRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AgendaItensService {

    @Autowired
    lateinit var agendaItensRepository: AgendaItensRepository

    fun buscarTodos(): MutableList<AgendaItens> {
        return agendaItensRepository.findAll() as MutableList<AgendaItens>
    }

    fun burcasTodosPorGuid(guid: String): MutableList<AgendaItens>? {
        return  agendaItensRepository.findByAgenda(guid)
    }

    fun buscarPorGuid(guid: String): AgendaItens? {
        val optional = agendaItensRepository.findByGuid(guid)
        if (optional.isEmpty) {
            return null
        }
        return optional.get()
    }

    fun salvar(agendaItensVO: AgendaItensVO): AgendaItens {
        val agendaItens = AgendaItens()
//        agendaItens.guid          = UUID.randomUUID().toString()
        agendaItens.guid          = agendaItensVO.guid
        agendaItens.cliente       = agendaItensVO.cliente
        agendaItens.veiculo       = agendaItensVO.veiculo
        agendaItens.agenda        = agendaItensVO.agenda
        agendaItens.atividade     = agendaItensVO.atividade
        agendaItens.tipo          = agendaItensVO.tipo
        agendaItens.status        = agendaItensVO.status
        agendaItens.observacao    = agendaItensVO.observacao
        agendaItens.criado        = Calendar.getInstance().formataParaBrasileiro()

        return agendaItensRepository.save(agendaItens)
    }

    fun atualizar(agendaItensVO: AgendaItensVO,
                  guid: String): AgendaItens {

        val agendaItens = buscarPorGuid(guid) ?: throw Exception("AgendaItens não encontrado para atualização")

        agendaItens.cliente       = agendaItensVO.cliente
        agendaItens.agenda        = agendaItensVO.agenda
        agendaItens.atividade     = agendaItensVO.atividade
        agendaItens.veiculo       = agendaItensVO.veiculo
        agendaItens.tipo          = agendaItensVO.tipo
        agendaItens.status        = agendaItensVO.status
        agendaItens.observacao    = agendaItensVO.observacao
        agendaItens.atualizado    = Calendar.getInstance().formataParaBrasileiro()

        return agendaItensRepository.save(agendaItens)
    }

    fun deletarPorGuid(guid: String) {
        val agendaitensLocalizado =  buscarPorGuid(guid) ?: throw Exception("AgendaItens não encontrado para Excluir")
        agendaitensLocalizado.excluido = Calendar.getInstance().formataParaBrasileiro()
        agendaItensRepository.delete(agendaitensLocalizado)
    }
}
