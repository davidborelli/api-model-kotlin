package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Patrimonio
import br.com.gimb.api.model.vo.PatrimonioVO
import br.com.gimb.api.repository.PatrimonioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class PatrimonioService {

    @Autowired
    lateinit var patrimonioRepository: PatrimonioRepository

    fun buscarTodos(ativo: Boolean?): MutableList<Patrimonio> {
        return when (ativo) {
            null -> patrimonioRepository.findAllByExcluidoNull()
            else -> patrimonioRepository.findByAtivoAndExcluidoNull(ativo)
        }
    }

    fun buscarPorId(id: Long): Patrimonio? {
        val optional = patrimonioRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()

        return null
    }

    fun salvar(patrimonioVO: PatrimonioVO): Patrimonio {

        if (patrimonioVO.almoxarifado.toInt() == 0 ||patrimonioVO.tipoPatrimonio.toInt() == 0) {
            throw Exception("Obrigatório informar Almoxarifado e TipoPatrimomnio")
        }

        val patrimonio = Patrimonio()
        patrimonio.nome              = patrimonioVO.nome
        patrimonio.identificador     = patrimonioVO.identificador
        patrimonio.numeroSerial      = patrimonioVO.numeroSerial
        patrimonio.ativo             = patrimonioVO.ativo
        patrimonio.almoxarifado.id   = patrimonioVO.almoxarifado
        patrimonio.localizacao       = patrimonioVO.localizacao
        patrimonio.numeroPatrimonio  = patrimonioVO.numeroPatrimonio
        patrimonio.tipoPatrimonio.id = patrimonioVO.tipoPatrimonio
        patrimonio.criado            = Calendar.getInstance().formataParaBrasileiro()

        return patrimonioRepository.save(patrimonio)
    }

    fun atualizar(patrimonioVO: PatrimonioVO,
                  id: Long): Patrimonio {

        if (patrimonioVO.almoxarifado.toInt() == 0 ||patrimonioVO.tipoPatrimonio.toInt() == 0) {
            throw Exception("Obrigatório informar Almoxarifado e TipoPatrimomnio")
        }

        val patrimonio = buscarPorId(id) ?: throw Exception("Patrimonio não encontrado para atualização")
        patrimonio.nome              = patrimonioVO.nome
        patrimonio.identificador     = patrimonioVO.identificador
        patrimonio.numeroSerial      = patrimonioVO.numeroSerial
        patrimonio.ativo             = patrimonioVO.ativo
        patrimonio.almoxarifado.id   = patrimonioVO.almoxarifado
        patrimonio.localizacao       = patrimonioVO.localizacao
        patrimonio.numeroPatrimonio  = patrimonioVO.numeroPatrimonio
        patrimonio.tipoPatrimonio.id = patrimonioVO.tipoPatrimonio
        patrimonio.atualizado        = Calendar.getInstance().formataParaBrasileiro()

        return patrimonioRepository.save(patrimonio)
    }

    fun deletar(id: Long){
        val patrimonio = buscarPorId(id) ?: throw Exception("Patrimonio não encontrado para Exclusão")
        patrimonio.excluido = Calendar.getInstance().formataParaBrasileiro()
        patrimonioRepository.save(patrimonio)
    }
}
