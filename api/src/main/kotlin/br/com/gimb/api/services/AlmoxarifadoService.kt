package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Almoxarifado
import br.com.gimb.api.model.vo.AlmoxarifadoVO
import br.com.gimb.api.repository.AlmoxarifadoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class AlmoxarifadoService {

    @Autowired
    lateinit var almoxarifadoRepository: AlmoxarifadoRepository

    fun buscarTodos(ativo: Boolean?): MutableList<Almoxarifado> {
        return when (ativo) {
            null -> almoxarifadoRepository.findAllByExcluidoNull()
            else -> almoxarifadoRepository.findByAtivoAndExcluidoNull(ativo)
        }
    }

    fun buscarPorId(id: Long): Almoxarifado? {
        val optional = almoxarifadoRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(almoxarifadoVo: AlmoxarifadoVO): Almoxarifado {
        val almoxarifado = Almoxarifado()
        almoxarifado.ativo  = almoxarifadoVo.ativo
        almoxarifado.cor    = almoxarifadoVo.cor
        almoxarifado.nome   = almoxarifadoVo.nome
        almoxarifado.criado = Calendar.getInstance().formataParaBrasileiro()

        return almoxarifadoRepository.save(almoxarifado)
    }

    fun atualizar(almoxarifadoVo: AlmoxarifadoVO,
                  id: Long): Almoxarifado {

        val almoxarifado = buscarPorId(id) ?: throw Exception("Almoxarifado não encontrado para atualização")

        almoxarifado.ativo      = almoxarifadoVo.ativo
        almoxarifado.cor        = almoxarifadoVo.cor
        almoxarifado.nome       = almoxarifadoVo.nome
        almoxarifado.atualizado = Calendar.getInstance().formataParaBrasileiro()

        return almoxarifadoRepository.save(almoxarifado)
    }

    fun deletarPorId(id: Long) {
        val almoxarifadoLocalizado =  buscarPorId(id) ?: throw Exception("Almoxarifado não encontrado para Excluir")
        almoxarifadoLocalizado.excluido = Calendar.getInstance().formataParaBrasileiro()
        almoxarifadoRepository.save(almoxarifadoLocalizado)
    }
}
