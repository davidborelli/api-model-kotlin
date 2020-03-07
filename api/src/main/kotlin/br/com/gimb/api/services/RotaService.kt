package br.com.gimb.api.services

import br.com.gimb.api.model.Rota
import br.com.gimb.api.model.vo.RotaVO
import br.com.gimb.api.repository.RotaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RotaService {

    @Autowired
    lateinit var rotaRepository: RotaRepository

    fun buscarTodos(): MutableList<Rota> {
        return rotaRepository.findAllByExcluidoNull()
    }

    fun buscarPorId(id: Long): Rota? {
        val optional = rotaRepository.findById(id)
        if (optional.isPresent)
            return optional.get()
        return null
    }

    fun salvar(rotaVO: RotaVO): Rota {

        val rota = Rota()
        rota.hora       = rotaVO.hora
        rota.latitude   = rotaVO.latitude
        rota.longitude  = rotaVO.longitude
        rota.status     = rotaVO.status
        rota.usuario.id = rotaVO.usuario

        return rotaRepository.save(rota)
    }

    fun atualizar(rotaVO: RotaVO,
                  id: Long): Rota {

        val rota = buscarPorId(id) ?: throw Exception("Rota não encontrada para atualização")
        rota.hora       = rotaVO.hora
        rota.latitude   = rotaVO.latitude
        rota.longitude  = rotaVO.longitude
        rota.status     = rotaVO.status
        rota.usuario.id = rotaVO.usuario

        return rotaRepository.save(rota)
    }

    fun deletar(id: Long) {
        return rotaRepository.deleteById(id)
    }
}
