package br.com.gimb.api.model

class AgendaItensVO(val guid: String = "",
                    val agenda: String = "",
                    val cliente: Cliente? = Cliente(),
                    val veiculo: Veiculo = Veiculo(),
                    val atividade: Atividade = Atividade(),
                    val tipo: String = "",
                    val status: String = "",
                    val observacao: String = "") {
}
