package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Equipamento
import br.com.gimb.api.model.vo.EquipamentoVO
import br.com.gimb.api.repository.EquipamentoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class EquipamentoService {

    @Autowired
    lateinit var equipamentoRepository: EquipamentoRepository

    fun buscarTodos(): MutableList<Equipamento> {
        return equipamentoRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): Equipamento? {
        val optional = equipamentoRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(equipamentoVO: EquipamentoVO): Equipamento {

        if (equipamentoVO.tipoEquipamento.toInt() == 0 || equipamentoVO.cliente.toInt() == 0){
            throw Exception("Campos obrigatórios: TipoEquipamento e Cliente")
        }

        val equipamento = Equipamento()
        equipamento.nome               = equipamentoVO.nome
        equipamento.tipoEquipamento.id = equipamentoVO.tipoEquipamento
        equipamento.marca              = equipamentoVO.marca
        equipamento.identificador      = equipamentoVO.identificador
        equipamento.modelo             = equipamentoVO.modelo
        equipamento.anoModelo          = equipamentoVO.anoModelo
        equipamento.anoFabricacao      = equipamentoVO.anoFabricacao
        equipamento.cliente.id         = equipamentoVO.cliente
        equipamento.criado             = Calendar.getInstance().formataParaBrasileiro()

        return equipamentoRepository.save(equipamento)
    }

    fun atualizar(equipamentoVO: EquipamentoVO,
                  id: Long): Equipamento {

        if (equipamentoVO.tipoEquipamento.toInt() == 0 || equipamentoVO.cliente.toInt() == 0){
            throw Exception("Campos obrigatórios: TipoEquipamento e Cliente")
        }

        val equipamento = buscarPorId(id) ?: throw Exception("Equipamento não encontrado para atualização")
        equipamento.nome               = equipamentoVO.nome
        equipamento.tipoEquipamento.id = equipamentoVO.tipoEquipamento
        equipamento.marca              = equipamentoVO.marca
        equipamento.identificador      = equipamentoVO.identificador
        equipamento.modelo             = equipamentoVO.modelo
        equipamento.anoModelo          = equipamentoVO.anoModelo
        equipamento.anoFabricacao      = equipamentoVO.anoFabricacao
        equipamento.cliente.id         = equipamentoVO.cliente
        equipamento.atualizado         = Calendar.getInstance().formataParaBrasileiro()

        return equipamentoRepository.save(equipamento)
    }

    fun deletar(id: Long) {
        val equipamento = buscarPorId(id) ?: throw Exception("Equipamento não encontrado para Exclusão")
        equipamento.excluido = Calendar.getInstance().formataParaBrasileiro()
        equipamentoRepository.save(equipamento)
    }
}
