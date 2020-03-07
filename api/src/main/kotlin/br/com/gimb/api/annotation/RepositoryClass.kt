package br.com.gimb.api.annotation

import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
annotation class RepositoryClass(val clazz: KClass<*>)

