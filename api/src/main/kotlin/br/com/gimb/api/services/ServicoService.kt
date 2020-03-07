package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Servico
import br.com.gimb.api.model.vo.ServicoVO
import br.com.gimb.api.repository.RotaRepository
import br.com.gimb.api.repository.ServicoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.spi.CalendarNameProvider

@Service
class ServicoService {

    @Autowired
    lateinit var servicoRepository: ServicoRepository

    fun buscarTodos(): MutableList<Servico> {
        return servicoRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): Servico? {
        val optional = servicoRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(servicoVO: ServicoVO): Servico {

        if (servicoVO.cliente.toInt() == 0 || servicoVO.usuario.toInt() == 0 ||
            servicoVO.atividade.toInt() == 0 || servicoVO.veiculo.toInt() == 0) {
          throw Exception("Obrigatório informar Cliente/Usuarii/Atividade/Veiculo")
        }

        val servico = Servico()
        servico.tempoInicio    = servicoVO.tempoInicio
        servico.tempoFim       = servicoVO.tempoFim
        servico.tempoInicioLat = servicoVO.tempoInicioLat
        servico.tempoFimLat    = servicoVO.tempoFimLat
        servico.tempoInicioLgt = servicoVO.tempoInicioLgt
        servico.tempoFimLgt    = servicoVO.tempoFimLgt
        servico.status         = servicoVO.status
        servico.cliente.id     = servicoVO.cliente
        servico.usuario.id     = servicoVO.usuario
        servico.atividade.id   = servicoVO.atividade
        servico.tag            = servicoVO.tag
        servico.veiculo.id     = servicoVO.veiculo
        servico.criado         = Calendar.getInstance().formataParaBrasileiro()

        return servicoRepository.save(servico)
    }

    fun atualizar(servicoVO: ServicoVO,
                  id: Long): Servico {

        if (servicoVO.cliente.toInt() == 0 || servicoVO.usuario.toInt() == 0 ||
                servicoVO.atividade.toInt() == 0 || servicoVO.veiculo.toInt() == 0) {
            throw Exception("Obrigatório informar Cliente/Usuarii/Atividade/Veiculo")
        }

        val servico = buscarPorId(id) ?: throw Exception("Servico não encontrado para atualização")
        servico.tempoInicio    = servicoVO.tempoInicio
        servico.tempoFim       = servicoVO.tempoFim
        servico.tempoInicioLat = servicoVO.tempoInicioLat
        servico.tempoFimLat    = servicoVO.tempoFimLat
        servico.tempoInicioLgt = servicoVO.tempoInicioLgt
        servico.tempoFimLgt    = servicoVO.tempoFimLgt
        servico.status         = servicoVO.status
        servico.cliente.id     = servicoVO.cliente
        servico.usuario.id     = servicoVO.usuario
        servico.atividade.id   = servicoVO.atividade
        servico.tag            = servicoVO.tag
        servico.veiculo.id     = servicoVO.veiculo
        servico.atualizado     = Calendar.getInstance().formataParaBrasileiro()

        return servicoRepository.save(servico)
    }

    fun deletar(id: Long) {
        val servico = buscarPorId(id) ?: throw Exception("Servico não encontrado para Exclusão")
        servico.excluido = Calendar.getInstance().formataParaBrasileiro()
        servicoRepository.save(servico)
    }
}
