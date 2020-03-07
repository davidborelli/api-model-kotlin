package br.com.gimb.api.repository.agenda

import br.com.gimb.api.model.Agenda
import br.com.gimb.api.repository.filter.AgendaFiltro
import br.com.gimb.api.services.ClienteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.StringUtils
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TypedQuery
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class AgendaRepositoryImpl : AgendaRepositoryQuery {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    override fun filtrar(agendaFiltro: AgendaFiltro): MutableList<Agenda> {
        val criteiraBuilder: CriteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery: CriteriaQuery<Agenda> = criteiraBuilder.createQuery(Agenda::class.java)

        val root: Root<Agenda> = criteriaQuery.from(Agenda::class.java)

        //Cria as restrições
        //var predicates: MutableList<Predicate> = criarRestricoes(agendaFiltro, criteiraBuilder, root)

        //Adiciona as restrições na Query
        criteriaQuery.where(
                criteiraBuilder.between(root.get("data"), agendaFiltro.dataDe!!.minusDays(1), agendaFiltro.dataAte)
        )

        val query: TypedQuery<Agenda> = entityManager.createQuery(criteriaQuery)

        val resultadoQuery = query.resultList

        return resultadoQuery
    }

    //Atualmente pesquisa apenas por 2 campos
    private fun criarRestricoes(agendaFiltro: AgendaFiltro, criteiraBuilder: CriteriaBuilder, root: Root<Agenda>): MutableList<Predicate> {

        val predicates = mutableListOf<Predicate>()

        if (!StringUtils.isEmpty(agendaFiltro.dataDe)) {
            predicates.add(
                    criteiraBuilder.greaterThanOrEqualTo(root.get("data"), agendaFiltro.dataDe)
            )
        }

        if (!StringUtils.isEmpty(agendaFiltro.dataAte)) {
            predicates.add(
                    criteiraBuilder.lessThanOrEqualTo(root.get("data"), agendaFiltro.dataAte)
            )
        }
        return predicates
    }
}
