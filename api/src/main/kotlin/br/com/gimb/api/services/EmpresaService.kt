package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Empresa
import br.com.gimb.api.model.vo.EmpresaVO
import br.com.gimb.api.repository.EmpresaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmpresaService {

    @Autowired
    lateinit var empresaRepository: EmpresaRepository

    fun buscarTodos(): MutableList<Empresa> {
        return empresaRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): Empresa? {
        val optional = empresaRepository.findByIdAndExcluidoNull(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(empresaVO: EmpresaVO): Empresa {

        val empresa = Empresa()

        empresa.razaoSocial  = empresaVO.razaoSocial
        empresa.nomeFantasia = empresaVO.nomeFantasia
        empresa.endereco     = empresaVO.endereco
        empresa.cidade       = empresaVO.cidade
        empresa.cnpj         = empresaVO.cnpj
        empresa.ie           = empresaVO.ie
        empresa.telefone     = empresaVO.telefone
        empresa.site         = empresaVO.site
        empresa.cep          = empresaVO.cep
        empresa.nomeLogo     = empresaVO.nomeLogo
        empresa.criado       = Calendar.getInstance().formataParaBrasileiro()

        return empresaRepository.save(empresa)
    }

    fun atualizar(empresaVO: EmpresaVO,
                  id: Long): Empresa {

        val empresa = buscarPorId(id) ?: throw Exception("Empresa não encontrado para atualização")

        empresa.razaoSocial  = empresaVO.razaoSocial
        empresa.nomeFantasia = empresaVO.nomeFantasia
        empresa.endereco     = empresaVO.endereco
        empresa.cidade       = empresaVO.cidade
        empresa.cnpj         = empresaVO.cnpj
        empresa.ie           = empresaVO.ie
        empresa.telefone     = empresaVO.telefone
        empresa.site         = empresaVO.site
        empresa.cep          = empresaVO.cep
        empresa.nomeLogo     = empresaVO.nomeLogo
        empresa.atualizado   = Calendar.getInstance().formataParaBrasileiro()

        return empresaRepository.save(empresa)
    }

    fun deletar(id: Long) {
        val empresa = buscarPorId(id) ?: throw Exception("Empresa não encontrado para Exclusão")
        empresa.excluido = Calendar.getInstance().formataParaBrasileiro()
        empresaRepository.save(empresa)
    }
}
