package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.DespesaItem
import br.com.gimb.api.model.vo.DespesaItemVO
import br.com.gimb.api.repository.DespesaItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class DespesaItemService {

    @Autowired
    lateinit var despesaItemRepository: DespesaItemRepository

    fun buscarTodos(): MutableList<DespesaItem> {
        return despesaItemRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): DespesaItem? {
        val optional = despesaItemRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()

        return null
    }

    fun salvar(despesaItemVO: DespesaItemVO): DespesaItem {

        if (despesaItemVO.evento.toInt() == 0 || despesaItemVO.usuario.toInt() == 0
                || despesaItemVO.relatorioDespesa.toInt() == 0 || despesaItemVO.despesa.toInt() == 0){
            throw Exception("Campos obrigatórios: Evento/Usuário/RelatórioDespesa/Despesa")
        }

        val despesaItem = DespesaItem()

        despesaItem.data                = despesaItemVO.data
        despesaItem.anotacao            = despesaItemVO.anotacao
        despesaItem.quantidade          = despesaItemVO.quantidade
        despesaItem.valorUnitario       = despesaItemVO.valorUnitario
        despesaItem.valorTotal          = despesaItemVO.valorTotal
        despesaItem.evento.id           = despesaItemVO.evento
        despesaItem.usuario.id          = despesaItemVO.usuario
        despesaItem.relatorioDespesa.id = despesaItemVO.relatorioDespesa
        despesaItem.despesa.id          = despesaItemVO.despesa
        despesaItem.criado              = Calendar.getInstance().formataParaBrasileiro()

        return despesaItemRepository.save(despesaItem)
    }

    fun atualizar(despesaItemVO: DespesaItemVO,
                  id: Long): DespesaItem {

        if (despesaItemVO.evento.toInt() == 0 || despesaItemVO.usuario.toInt() == 0
                || despesaItemVO.relatorioDespesa.toInt() == 0 || despesaItemVO.despesa.toInt() == 0){
            throw Exception("Campos obrigatórios: Evento/Usuário/RelatórioDespesa/Despesa")
        }

        val despesaItem = buscarPorId(id) ?: throw Exception("Despesa Item não encontrado para atualização")

        despesaItem.data                = despesaItemVO.data
        despesaItem.anotacao            = despesaItemVO.anotacao
        despesaItem.quantidade          = despesaItemVO.quantidade
        despesaItem.valorUnitario       = despesaItemVO.valorUnitario
        despesaItem.valorTotal          = despesaItemVO.valorTotal
        despesaItem.evento.id           = despesaItemVO.evento
        despesaItem.usuario.id          = despesaItemVO.usuario
        despesaItem.relatorioDespesa.id = despesaItemVO.relatorioDespesa
        despesaItem.despesa.id          = despesaItemVO.despesa
        despesaItem.atualizado          = Calendar.getInstance().formataParaBrasileiro()

        return despesaItemRepository.save(despesaItem)
    }

    fun delete(id: Long) {
        val despesaItem = buscarPorId(id) ?: throw Exception("Despesa Item não encontrado para Exclusão")
        despesaItem.excluido = Calendar.getInstance().formataParaBrasileiro()
        despesaItemRepository.save(despesaItem)
    }

}
