package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.FotoPersonalizado
import br.com.gimb.api.model.vo.FotoPersonalizadoVO
import br.com.gimb.api.repository.FotoPersonalizadoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class FotoPersonalizadoService {

    @Autowired
    lateinit var fotoPersonalizadoRepository: FotoPersonalizadoRepository

    fun buscarTodos(): MutableList<FotoPersonalizado>{
        return fotoPersonalizadoRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): FotoPersonalizado? {
        val optional = fotoPersonalizadoRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()

        return null
    }

    fun salvar(fotoPersonalizadoVO: FotoPersonalizadoVO): FotoPersonalizado {
        if (fotoPersonalizadoVO.campoPersonalizado.toInt() == 0) {
            throw Exception("Campo de referência CampoPerinalizado é obrigatório")
        }

        val fotoPersonalizado = FotoPersonalizado()
        fotoPersonalizado.campoPersonalizado.id = fotoPersonalizadoVO.campoPersonalizado
        fotoPersonalizado.referenciaId          = fotoPersonalizadoVO.referenciaId
        fotoPersonalizado.referenciaTipo        = fotoPersonalizadoVO.referenciaTipo
        fotoPersonalizado.latitude              = fotoPersonalizadoVO.latitude
        fotoPersonalizado.longitude             = fotoPersonalizadoVO.longitude
        fotoPersonalizado.fotoExtensao          = fotoPersonalizadoVO.fotoExtensao
        fotoPersonalizado.fixFotoExtensao       = fotoPersonalizadoVO.fixFotoExtensao
        fotoPersonalizado.fotoNome              = fotoPersonalizadoVO.fotoNome
        fotoPersonalizado.fixFotoNome           = fotoPersonalizadoVO.fixFotoNome
        fotoPersonalizado.fotoCaminho           = fotoPersonalizadoVO.fotoCaminho
        fotoPersonalizado.fixFotoCaminho        = fotoPersonalizadoVO.fixFotoCaminho
        fotoPersonalizado.mensagem              = fotoPersonalizadoVO.mensagem
        fotoPersonalizado.manualmente           = fotoPersonalizadoVO.manualmente
        fotoPersonalizado.observacao            = fotoPersonalizadoVO.observacao
        fotoPersonalizado.status_checklist      = fotoPersonalizadoVO.status_checklist
        fotoPersonalizado.statusFix             = fotoPersonalizadoVO.statusFix
        fotoPersonalizado.criado                = Calendar.getInstance().formataParaBrasileiro()

        return fotoPersonalizadoRepository.save(fotoPersonalizado)
    }

    fun atualizar(fotoPersonalizadoVO: FotoPersonalizadoVO,
                  id: Long): FotoPersonalizado {

        if (fotoPersonalizadoVO.campoPersonalizado.toInt() == 0) {
            throw Exception("Campo de referência CampoPerinalizado é obrigatório")
        }

        val fotoPersonalizado = buscarPorId(id) ?: throw Exception("Foto Personalizado não encontrado para atualização")
        fotoPersonalizado.campoPersonalizado.id = fotoPersonalizadoVO.campoPersonalizado
        fotoPersonalizado.referenciaId          = fotoPersonalizadoVO.referenciaId
        fotoPersonalizado.referenciaTipo        = fotoPersonalizadoVO.referenciaTipo
        fotoPersonalizado.latitude              = fotoPersonalizadoVO.latitude
        fotoPersonalizado.longitude             = fotoPersonalizadoVO.longitude
        fotoPersonalizado.fotoExtensao          = fotoPersonalizadoVO.fotoExtensao
        fotoPersonalizado.fixFotoExtensao       = fotoPersonalizadoVO.fixFotoExtensao
        fotoPersonalizado.fotoNome              = fotoPersonalizadoVO.fotoNome
        fotoPersonalizado.fixFotoNome           = fotoPersonalizadoVO.fixFotoNome
        fotoPersonalizado.fotoCaminho           = fotoPersonalizadoVO.fotoCaminho
        fotoPersonalizado.fixFotoCaminho        = fotoPersonalizadoVO.fixFotoCaminho
        fotoPersonalizado.mensagem              = fotoPersonalizadoVO.mensagem
        fotoPersonalizado.manualmente           = fotoPersonalizadoVO.manualmente
        fotoPersonalizado.observacao            = fotoPersonalizadoVO.observacao
        fotoPersonalizado.status_checklist      = fotoPersonalizadoVO.status_checklist
        fotoPersonalizado.statusFix             = fotoPersonalizadoVO.statusFix
        fotoPersonalizado.atualizado            = Calendar.getInstance().formataParaBrasileiro()

        return fotoPersonalizadoRepository.save(fotoPersonalizado)
    }

    fun delete(id: Long){
        val fotoPersonalizado = buscarPorId(id) ?: throw Exception("Foto Personalizado não encontrado para Exclusão")
        fotoPersonalizado.excluido = Calendar.getInstance().formataParaBrasileiro()
        fotoPersonalizadoRepository.save(fotoPersonalizado)
    }

}
