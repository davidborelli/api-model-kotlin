package br.com.gimb.api.repository

import br.com.gimb.api.model.Parametro
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ParametrosRepository:  CrudRepository<Parametro, String>{
    override fun findAll(): MutableList<Parametro>
}
