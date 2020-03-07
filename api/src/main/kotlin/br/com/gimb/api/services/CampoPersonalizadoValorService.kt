package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.CampoPersonalizadoValor
import br.com.gimb.api.model.vo.CampoPersonalizadoValorVO
import br.com.gimb.api.repository.CampoPersonalizadoValorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CampoPersonalizadoValorService {

    @Autowired
    lateinit var campoPersonalizadoValorRepository: CampoPersonalizadoValorRepository

    fun buscarTodos(): MutableList<CampoPersonalizadoValor> {
        return campoPersonalizadoValorRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): CampoPersonalizadoValor? {
        val optional = campoPersonalizadoValorRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(campoPersonalizadoValorVO: CampoPersonalizadoValorVO): CampoPersonalizadoValor {

        if (campoPersonalizadoValorVO.campoPersonalizado.toInt() == 0)
            throw Exception("É preciso informar referência do Campo Personalizado")

        val campoPersonalizadoValor = CampoPersonalizadoValor()
        campoPersonalizadoValor.campoPersonalizado.id = campoPersonalizadoValorVO.campoPersonalizado
        campoPersonalizadoValor.referenciaTipo        = campoPersonalizadoValorVO.referenciaTipo
        campoPersonalizadoValor.referencia_id         = campoPersonalizadoValorVO.referencia_id
        campoPersonalizadoValor.valor                 = campoPersonalizadoValorVO.valor
        campoPersonalizadoValor.criado                = Calendar.getInstance().formataParaBrasileiro()

        return campoPersonalizadoValorRepository.save(campoPersonalizadoValor)
    }

    fun atualizar(campoPersonalizadoValorVO: CampoPersonalizadoValorVO,
                  id: Long): CampoPersonalizadoValor {

        if (campoPersonalizadoValorVO.campoPersonalizado.toInt() == 0)
            throw Exception("É preciso informar referência do Campo Personalizado")

        val campoPersonalizadoValor = buscarPorId(id) ?: throw Exception("Campo Personalizado Valor não encontrado para atualização")
        campoPersonalizadoValor.campoPersonalizado.id = campoPersonalizadoValorVO.campoPersonalizado
        campoPersonalizadoValor.referenciaTipo        = campoPersonalizadoValorVO.referenciaTipo
        campoPersonalizadoValor.referencia_id         = campoPersonalizadoValorVO.referencia_id
        campoPersonalizadoValor.valor                 = campoPersonalizadoValorVO.valor
        campoPersonalizadoValor.atualizado            = Calendar.getInstance().formataParaBrasileiro()

        return campoPersonalizadoValorRepository.save(campoPersonalizadoValor)
    }

    fun deletar(id: Long) {
        val campoPersonalizadoValor = buscarPorId(id) ?: throw Exception("Campo Personalizado Valor não encontrado para Exclusão")
        campoPersonalizadoValor.excluido = Calendar.getInstance().formataParaBrasileiro()
        campoPersonalizadoValorRepository.save(campoPersonalizadoValor)
    }


}
