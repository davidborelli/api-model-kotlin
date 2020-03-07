package br.com.gimb.api.services

import br.com.gimb.api.annotation.IgnoreUpperCase
import br.com.gimb.api.model.BaseModel
import br.com.gimb.api.repository.BaseRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
open class BaseService<T : BaseModel> {

    @Autowired
    lateinit var repository: BaseRepository<T>

    fun findAll(): List<T> {
        return repository.findAll().toList()
    }

    fun findById(id: Long): T? {
        return if (repository.findById(id).isPresent) repository.findById(id).get() else null
    }

    fun delete(entity: T) {
        repository.delete(entity)
    }

    fun <S : Any> save(entity: T): T? {
        try {
            for (f in entity.javaClass.declaredFields) {
                f.isAccessible = true
                if (f.get(entity) == null) continue

                if (f.isAnnotationPresent(IgnoreUpperCase::class.java)) continue

                if (f.type == String::class.java) {
                    val value = f.get(entity).toString()
                    f.set(entity, value.toUpperCase())
                }
            }

            return repository.save(entity)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

}
