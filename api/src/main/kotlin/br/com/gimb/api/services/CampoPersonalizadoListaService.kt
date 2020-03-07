package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.CampoPersonalizadoLista
import br.com.gimb.api.model.vo.CampoPersonalizadoListaVO
import br.com.gimb.api.repository.CampoPersonalizadoListaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CampoPersonalizadoListaService {

    @Autowired
    lateinit var campoPersonalizadoListaRepository: CampoPersonalizadoListaRepository

    fun buscarTodos(): MutableList<CampoPersonalizadoLista> {
        return campoPersonalizadoListaRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): CampoPersonalizadoLista? {
        val optional = campoPersonalizadoListaRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(campoPersonalizadoListaVO: CampoPersonalizadoListaVO): CampoPersonalizadoLista {

        if (campoPersonalizadoListaVO.campoPersonalizado.toInt() == 0)
            throw Exception("Deve ser informado a referência do Campo Personalizado")

        val campoPersonalizadoLista = CampoPersonalizadoLista()
        campoPersonalizadoLista.campoPersonalizado.id = campoPersonalizadoListaVO.campoPersonalizado
        campoPersonalizadoLista.descricao             = campoPersonalizadoListaVO.descricao
        campoPersonalizadoLista.tipoCampo             = campoPersonalizadoListaVO.tipoCampo
        campoPersonalizadoLista.valores               = campoPersonalizadoListaVO.valores
        campoPersonalizadoLista.criado                = Calendar.getInstance().formataParaBrasileiro()

        return campoPersonalizadoListaRepository.save(campoPersonalizadoLista)
    }

    fun atualizar(campoPersonalizadoListaVO: CampoPersonalizadoListaVO,
                  id: Long): CampoPersonalizadoLista {

        if (campoPersonalizadoListaVO.campoPersonalizado.toInt() == 0)
            throw Exception("Deve ser informado a referência do Campo Personalizado")

        val campoPersonalizadoLista = buscarPorId(id) ?: throw Exception("Campo Personalizados Lista não encontrado para atualização")
        campoPersonalizadoLista.campoPersonalizado.id = campoPersonalizadoListaVO.campoPersonalizado
        campoPersonalizadoLista.descricao             = campoPersonalizadoListaVO.descricao
        campoPersonalizadoLista.tipoCampo             = campoPersonalizadoListaVO.tipoCampo
        campoPersonalizadoLista.valores               = campoPersonalizadoListaVO.valores
        campoPersonalizadoLista.atualizado            = Calendar.getInstance().formataParaBrasileiro()

        return campoPersonalizadoListaRepository.save(campoPersonalizadoLista)
    }

    fun deletar(id: Long) {
        val campoPersonalizadoLista = buscarPorId(id) ?: throw Exception("Campo Personalizados Lista não encontrado para Exclusão")
        campoPersonalizadoLista.excluido = Calendar.getInstance().formataParaBrasileiro()
        campoPersonalizadoListaRepository.save(campoPersonalizadoLista)
    }


}
