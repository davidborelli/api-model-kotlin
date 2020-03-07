//package br.com.gimb.api.repository.atividade
//
//import br.com.gimb.api.model.Atividade
//import br.com.gimb.api.repository.filter.AtividadeFilter
//import org.springframework.data.domain.Page
//import org.springframework.data.domain.PageImpl
//import org.springframework.data.domain.Pageable
//import org.springframework.util.StringUtils
//import javax.persistence.EntityManager
//import javax.persistence.PersistenceContext
//import javax.persistence.TypedQuery
//import javax.persistence.criteria.CriteriaBuilder
//import javax.persistence.criteria.CriteriaQuery
//import javax.persistence.criteria.Predicate
//import javax.persistence.criteria.Root
//import java.util.ArrayList
//
//
//
//open class AtividadeRepositoryImpl(@PersistenceContext val manager: EntityManager) : AtividadeRepositoryQuery {
//
//
//    override fun filtrar(atividadeFilter: AtividadeFilter, pageable: Pageable): Page<Atividade> {
//        val builder: CriteriaBuilder = manager.criteriaBuilder
//
//        val criteria: CriteriaQuery<Atividade> = builder.createQuery(Atividade::class.java)
//        val root: Root<Atividade> = criteria.from(Atividade::class.java)
//
//        val predicates = criarRestricoes(atividadeFilter, builder, root)
//        criteria.where(predicates)
//
//        val query: TypedQuery<Atividade> = manager.createQuery(criteria)
//        adicionarRestricoesDePaginacao(query, pageable)
//
//        return PageImpl(query.resultList, pageable, total(atividadeFilter))
//
//    }
//
//    private fun criarRestricoes(atividadeFilter: AtividadeFilter, builder: CriteriaBuilder, root: Root<Atividade>): Array<Predicate> {
//        val predicates = ArrayList<Predicate>()
//
//        if (!StringUtils.isEmpty(atividadeFilter.nome)) {
//            predicates.add(builder.like(atividadeFilter., "%"+atividadeFilter.nome+"%"))
//        }
//
//        return predicates.toTypedArray()
//    }
//
//    private fun adicionarRestricoesDePaginacao(query: TypedQuery<Atividade>, pageable: Pageable ) {
//        val paginaAtual = pageable.pageNumber
//        val totalDeRegistrosPorPagina = pageable.pageSize
//        val primeiroRegistroDaPagina = paginaAtual * totalDeRegistrosPorPagina
//
//        query.firstResult = primeiroRegistroDaPagina
//        query.maxResults = totalDeRegistrosPorPagina
//    }
//
//    private fun total(atividadeFilter: AtividadeFilter): Long {
//        val builder  = manager.criteriaBuilder
//        val criteria = builder.createQuery(Long::class.java)
//        val root = criteria.from(Atividade::class.java)
//
//        val predicates = criarRestricoes(atividadeFilter, builder, root)
//        criteria.where(predicates)
//
//        criteria.select(builder.count(root))
//        return manager.createQuery(criteria).singleResult
//    }
//
//}
