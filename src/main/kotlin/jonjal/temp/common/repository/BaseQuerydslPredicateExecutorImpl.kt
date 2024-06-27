package jonjal.temp.common.repository

import com.querydsl.core.types.EntityPath
import com.querydsl.core.types.Predicate
import com.querydsl.core.types.QBean
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.core.types.dsl.PathBuilder
import com.querydsl.jpa.JPQLQuery
import com.querydsl.jpa.impl.JPAQuery
import jakarta.persistence.EntityManager
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.Querydsl
import org.springframework.data.querydsl.EntityPathResolver
import org.springframework.data.querydsl.SimpleEntityPathResolver
import java.io.Serializable

class BaseQuerydslPredicateExecutorImpl<DOMAIN, ID : Serializable?>(
    private val entityInformation: JpaEntityInformation<DOMAIN, *>,
    private val entityManager: EntityManager
) : BaseQuerydslPredicateExecutor<DOMAIN, ID> {

    private val path: EntityPath<DOMAIN> = resolver.createPath(entityInformation.javaType)
    private val builder: PathBuilder<DOMAIN> = PathBuilder<DOMAIN>(path.type, path.metadata)
    private val querydsl: Querydsl = Querydsl(entityManager, builder)


    companion object {
        val resolver: EntityPathResolver = SimpleEntityPathResolver.INSTANCE
    }

    override fun from(): JPQLQuery<DOMAIN> {
        return querydsl.createQuery(path).select(path)
    }

    override fun <DTO> getPagingList(
        pageable: Pageable,
        projection: QBean<DTO>,
        query: JPAQuery<DOMAIN>
    ): Page<DTO> {
        val results = querydsl.applyPagination<DOMAIN>(
            pageable,
            query
        ).select(
            projection
        ).fetchResults()
        return PageImpl(results.results, pageable, results.total)
    }

    override fun <DTO> getPagingList(
        pageable: Pageable,
        projection: QBean<DTO>,
        query: JPQLQuery<DOMAIN>
    ): Page<DTO> {
        val results = querydsl.applyPagination<DOMAIN>(
            pageable,
            query
        ).select(
            projection
        ).fetchResults()
        return PageImpl(results.results, pageable, results.total)
    }


    override fun <DTO> getPagingList(
        pageable: Pageable,
        projection: QBean<DTO>,
        contentQuery: JPQLQuery<DOMAIN>,
        countQuery: JPQLQuery<DOMAIN>
    ): Page<DTO> {
        val results = querydsl.applyPagination<DOMAIN>(
            pageable,
            contentQuery
        ).select(
            projection
        ).fetch()
        return PageImpl(results, pageable, countQuery.fetchCount())
    }

    override fun count(query: JPQLQuery<DOMAIN>): Long {
        return query.fetchCount()
    }

    override fun count(predicate: Predicate?): Long {
        return from()
            .where(predicate)
            .fetchCount()
    }

    override fun count(): Long {
        return from().fetchCount()
    }

    override fun exists(predicate: Predicate?): Boolean {
        return from().select(Expressions.ONE).where(predicate).fetchFirst() != null
    }
}