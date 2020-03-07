package br.com.gimb.api.services

import br.com.gimb.api.extension.formataParaBrasileiro
import br.com.gimb.api.model.Cliente
import br.com.gimb.api.model.vo.ClienteVO
import br.com.gimb.api.repository.ClienteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClienteService {

    @Autowired
    lateinit var clienteRepository: ClienteRepository


    fun buscarTodos(ativo: Boolean?): MutableList<Cliente>? {
        return when (ativo) {
            null -> clienteRepository.findAllByExcluidoNull()
            else -> clienteRepository.findByAtivoAndExcluidoNull(ativo)
        }
    }

    fun buscarPorId(id: Long): Cliente? {
        val optional = clienteRepository.findByGuidAndExcluidoNull(id)

        if (optional.isPresent)
            return optional.get()

        return null
    }

    fun salvar(clienteVo: ClienteVO): Cliente {
        val cliente = Cliente()

        cliente.nome        = clienteVo.nome
        cliente.razaoSocial = clienteVo.razaoSocial
        cliente.ieRg        = clienteVo.ieRg
        cliente.cnpj_cpf    = clienteVo.cnpj_cpf
        cliente.endereco    = clienteVo.endereco
        cliente.telefone    = clienteVo.telefone
        cliente.cep         = clienteVo.cep
        cliente.cidade      = clienteVo.cidade
        cliente.senhaAcesso = clienteVo.senhaAcesso
        cliente.ativo       = clienteVo.ativo
        cliente.cor         = clienteVo.cor
        cliente.criado      = Calendar.getInstance().formataParaBrasileiro()

        return clienteRepository.save(cliente)
    }

    fun atualizar(clienteVo: ClienteVO,
                  id: Long): Cliente {

        val cliente = buscarPorId(id) ?: throw Exception("Cliente não encontrado para atualização")

        cliente.nome        = clienteVo.nome
        cliente.razaoSocial = clienteVo.razaoSocial
        cliente.ieRg        = clienteVo.ieRg
        cliente.cnpj_cpf    = clienteVo.cnpj_cpf
        cliente.endereco    = clienteVo.endereco
        cliente.telefone    = clienteVo.telefone
        cliente.cep         = clienteVo.cep
        cliente.cidade      = clienteVo.cidade
        cliente.senhaAcesso = clienteVo.senhaAcesso
        cliente.ativo       = clienteVo.ativo
        cliente.cor         = clienteVo.cor
        cliente.atualizado  = Calendar.getInstance().formataParaBrasileiro()

        return clienteRepository.save(cliente)
    }

    fun deletar(id: Long) {
        val cliente = buscarPorId(id) ?: throw Exception("Cliente não encontrado para Exclusão")
        cliente.excluido = Calendar.getInstance().formataParaBrasileiro()
        clienteRepository.save(cliente)
    }
}
