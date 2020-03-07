package br.com.gimb.api.model.vo

class ApontamentoEventoVO(val dataHora: String = "",
                          val latitude: String = "",
                          val longitude: String = "",
                          val apontamento: Long = 0,
                          val evento: Long = 0,
                          val tempoInicial: String = "",
                          val tempoFinal: String = "",
                          val tempoInicialOriginal: String = "",
                          val tempoFinalOriginal: String = "",
                          val observacao: String = "") {
}
