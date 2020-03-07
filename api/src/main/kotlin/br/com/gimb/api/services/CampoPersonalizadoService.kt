package br.com.gimb.api.services

import br.com.gimb.api.enumarated.CampoPersonalizadoReferenciaEnum
import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.CampoPersonalizado
import br.com.gimb.api.model.vo.CampoPersonalizadoVO
import br.com.gimb.api.repository.CampoPersonalizadoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.Exception

@Service
class CampoPersonalizadoService {

    @Autowired
    lateinit var campoPersonalizadoRepository: CampoPersonalizadoRepository

    fun buscarTodos(): MutableList<CampoPersonalizado> {
        return campoPersonalizadoRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): CampoPersonalizado? {
        val optional = campoPersonalizadoRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(campoPersonalizadoVO: CampoPersonalizadoVO,
               tipoReferencia: CampoPersonalizadoReferenciaEnum): CampoPersonalizado {

        if (tipoReferencia == CampoPersonalizadoReferenciaEnum.NENHUM)
            throw Exception()

        val campoPersonalizado = CampoPersonalizado()
        campoPersonalizado.descricao      = campoPersonalizadoVO.descricao
        campoPersonalizado.tipoReferencia = tipoReferencia
        campoPersonalizado.tipoCampo      = campoPersonalizadoVO.tipoCampo
        campoPersonalizado.idReferencia   = campoPersonalizadoVO.idReferencia
        campoPersonalizado.criado         = Calendar.getInstance().formataParaBrasileiro()

        return campoPersonalizadoRepository.save(campoPersonalizado)
    }

    fun atualizar(campoPersonalizadoVO: CampoPersonalizadoVO,
                  tipoReferencia: CampoPersonalizadoReferenciaEnum,
                  id: Long): CampoPersonalizado {

        if (tipoReferencia == CampoPersonalizadoReferenciaEnum.NENHUM)
            throw Exception()

        val campoPersonalizado = buscarPorId(id) ?: throw Exception("Campo Personalizado não encontrado para atualização")
        campoPersonalizado.descricao      = campoPersonalizadoVO.descricao
        campoPersonalizado.tipoReferencia = tipoReferencia
        campoPersonalizado.tipoCampo      = campoPersonalizadoVO.tipoCampo
        campoPersonalizado.idReferencia   = campoPersonalizadoVO.idReferencia
        campoPersonalizado.atualizado     = Calendar.getInstance().formataParaBrasileiro()

        return campoPersonalizadoRepository.save(campoPersonalizado)
    }

    fun deletar(id: Long) {
        val campoPersonalizado = buscarPorId(id) ?: throw Exception("Campo Personalizado não encontrado para Exclusão")
        campoPersonalizado.excluido = Calendar.getInstance().formataParaBrasileiro()
        campoPersonalizadoRepository.save(campoPersonalizado)
    }


}
