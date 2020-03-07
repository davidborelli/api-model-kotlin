package br.com.gimb.api.security.util

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

fun main(args : Array<String>) {
    val encoder = BCryptPasswordEncoder()
    println(encoder.encode("admin"))
}
