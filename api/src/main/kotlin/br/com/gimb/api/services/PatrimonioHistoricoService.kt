package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.PatrimonioHistorico
import br.com.gimb.api.model.vo.PatrimonioHistoricoVO
import br.com.gimb.api.repository.PatrimonioHistoricoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PatrimonioHistoricoService {

    @Autowired
    lateinit var patrimonioHistoricoRepository: PatrimonioHistoricoRepository

    fun buscarTodos(): MutableList<PatrimonioHistorico> {
        return patrimonioHistoricoRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): PatrimonioHistorico? {
        val optional = patrimonioHistoricoRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()

        return null
    }

    fun salvar(patrimonioHistoricoVO: PatrimonioHistoricoVO): PatrimonioHistorico {

        if (patrimonioHistoricoVO.patrimonio.toInt() == 0 ||patrimonioHistoricoVO.usuario.toInt() == 0) {
            throw Exception("Obrigatório informar Patrimonio e Usuario")
        }

        val patrimonioHistorico = PatrimonioHistorico()
        patrimonioHistorico.patrimonio.id = patrimonioHistoricoVO.patrimonio
        patrimonioHistorico.data          = patrimonioHistoricoVO.data
        patrimonioHistorico.localizacao   = patrimonioHistoricoVO.localizacao
        patrimonioHistorico.usuario.id    = patrimonioHistoricoVO.usuario
        patrimonioHistorico.observacao    = patrimonioHistoricoVO.observacao
        patrimonioHistorico.criado        = Calendar.getInstance().formataParaBrasileiro()

        return patrimonioHistoricoRepository.save(patrimonioHistorico)
    }

    fun atualizar(patrimonioHistoricoVO: PatrimonioHistoricoVO,
                  id: Long): PatrimonioHistorico {

        if (patrimonioHistoricoVO.patrimonio.toInt() == 0 ||patrimonioHistoricoVO.usuario.toInt() == 0) {
            throw Exception("Obrigatório informar Patrimonio e Usuario")
        }

        val patrimonioHistorico = buscarPorId(id) ?: throw Exception("Patrimonio Historico não encontrado para atualização")
        patrimonioHistorico.patrimonio.id = patrimonioHistoricoVO.patrimonio
        patrimonioHistorico.data          = patrimonioHistoricoVO.data
        patrimonioHistorico.localizacao   = patrimonioHistoricoVO.localizacao
        patrimonioHistorico.usuario.id    = patrimonioHistoricoVO.usuario
        patrimonioHistorico.observacao    = patrimonioHistoricoVO.observacao
        patrimonioHistorico.atualizado    = Calendar.getInstance().formataParaBrasileiro()

        return patrimonioHistoricoRepository.save(patrimonioHistorico)
    }

    fun deletar(id: Long){
        val patrimonioHistorico = buscarPorId(id) ?: throw Exception("Patrimonio Historico não encontrado para Exclusão")
        patrimonioHistorico.excluido = Calendar.getInstance().formataParaBrasileiro()
        patrimonioHistoricoRepository.save(patrimonioHistorico)
    }
}
