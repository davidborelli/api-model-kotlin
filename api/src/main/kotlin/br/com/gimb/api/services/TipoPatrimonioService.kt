package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.TipoPatrimonio
import br.com.gimb.api.model.vo.TipoPatrimonioVO
import br.com.gimb.api.repository.TipoPatrimonioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TipoPatrimonioService {

    @Autowired
    lateinit var tipoPatrimonioRepository: TipoPatrimonioRepository

    fun buscarTodos(ativo: Boolean): MutableList<TipoPatrimonio> {
        return when (ativo){
            null -> tipoPatrimonioRepository.findAllByExcluidoNull()
            else -> tipoPatrimonioRepository.findByAtivoAndExcluidoNull(ativo)
        }
    }

    fun buscarPorId(id: Long): TipoPatrimonio? {
        val optional = tipoPatrimonioRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(tipoPatrimonioVO: TipoPatrimonioVO): TipoPatrimonio {

        val tipoPatrimonio = TipoPatrimonio()
        tipoPatrimonio.nome   = tipoPatrimonioVO.nome
        tipoPatrimonio.ativo  = tipoPatrimonioVO.ativo
        tipoPatrimonio.cor    = tipoPatrimonioVO.cor
        tipoPatrimonio.criado = Calendar.getInstance().formataParaBrasileiro()

        return tipoPatrimonioRepository.save(tipoPatrimonio)
    }

    fun atualizar(tipoPatrimonioVO: TipoPatrimonioVO,
                  id: Long): TipoPatrimonio {

        val tipoPatrimonio = buscarPorId(id) ?: throw Exception("TipoPatrimonio não encontrado para atualização")
        tipoPatrimonio.nome       = tipoPatrimonioVO.nome
        tipoPatrimonio.ativo      = tipoPatrimonioVO.ativo
        tipoPatrimonio.cor        = tipoPatrimonioVO.cor
        tipoPatrimonio.atualizado = Calendar.getInstance().formataParaBrasileiro()

        return tipoPatrimonioRepository.save(tipoPatrimonio)
    }

    fun deletar(id: Long) {
        val tipoPatrimonio = buscarPorId(id) ?: throw Exception("TipoPatrimonio não encontrado para Exclusão")
        tipoPatrimonio.excluido = Calendar.getInstance().formataParaBrasileiro()
        tipoPatrimonioRepository.save(tipoPatrimonio)
    }
}
