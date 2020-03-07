package br.com.gimb.api.repository

import br.com.gimb.api.model.BaseModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BaseRepository<T : BaseModel> : CrudRepository<T, Long> {

}
