package br.com.gimb.api.extension

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.formataParaBrasileiro(): String {
    var formatoBrasileiro = "dd/MM/yyyy HH:mm:ss"
    val format = SimpleDateFormat(formatoBrasileiro)
    return format.format(this.time)
}
