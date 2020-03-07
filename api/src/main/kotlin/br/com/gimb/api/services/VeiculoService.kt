package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Veiculo
import br.com.gimb.api.model.vo.VeiculoVO
import br.com.gimb.api.repository.VeiculoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class VeiculoService {

    @Autowired
    lateinit var veiculoRepository: VeiculoRepository

    fun buscarTodos(): MutableList<Veiculo> {
        return veiculoRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): Veiculo? {
        val optional = veiculoRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(veiculoVO: VeiculoVO): Veiculo {

        if (veiculoVO.equipamento.toInt() == 0 || veiculoVO.cliente.toInt() == 0)
            throw Exception("Campo Equipamento e Cliente são obrigatórios")

        val veiculo = Veiculo()
        veiculo.marca          = veiculoVO.marca
        veiculo.anoFabricacao  = veiculoVO.anoFabricacao
        veiculo.modelo         = veiculoVO.modelo
        veiculo.anoModelo      = veiculoVO.anoModelo
        veiculo.placa          = veiculoVO.placa
        veiculo.tempoEstimado  = veiculoVO.tempoEstimado
        veiculo.cliente        = veiculoVO.cliente
        veiculo.equipamento    = veiculoVO.equipamento
        veiculo.criado         = Calendar.getInstance().formataParaBrasileiro()

        return veiculoRepository.save(veiculo)
    }

    fun atualizar(veiculoVO: VeiculoVO,
                  id: Long): Veiculo {

        if (veiculoVO.equipamento.toInt() == 0 || veiculoVO.cliente.toInt() == 0)
            throw Exception("Campo Equipamento e Cliente são obrigatórios")

        val veiculo = buscarPorId(id) ?: throw Exception("Veiculo não encontrado para atualização")
        veiculo.marca          = veiculoVO.marca
        veiculo.anoFabricacao  = veiculoVO.anoFabricacao
        veiculo.modelo         = veiculoVO.modelo
        veiculo.anoModelo      = veiculoVO.anoModelo
        veiculo.placa          = veiculoVO.placa
        veiculo.tempoEstimado  = veiculoVO.tempoEstimado
        veiculo.cliente        = veiculoVO.cliente
        veiculo.equipamento    = veiculoVO.equipamento
        veiculo.atualizado     = Calendar.getInstance().formataParaBrasileiro()

        return veiculoRepository.save(veiculo)
    }

    fun deletar(id: Long) {
        val veiculo = buscarPorId(id) ?: throw Exception("Veiculo não encontrado para Exclusão")
        veiculo.excluido = Calendar.getInstance().formataParaBrasileiro()
        veiculoRepository.save(veiculo)
    }
}
