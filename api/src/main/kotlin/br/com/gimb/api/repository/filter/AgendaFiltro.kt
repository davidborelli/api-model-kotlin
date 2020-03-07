package br.com.gimb.api.repository.filter

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

class AgendaFiltro {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var dataDe: LocalDate? = null

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var dataAte: LocalDate? = null
}
