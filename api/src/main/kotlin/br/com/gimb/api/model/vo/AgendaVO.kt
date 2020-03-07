package br.com.gimb.api.model

import java.time.LocalDate

class AgendaVO(val guid: String = "",
               val data: LocalDate = LocalDate.now(),
               val usuario: Usuario = Usuario()) { }
