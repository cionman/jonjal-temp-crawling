package jonjal.temp.common.repository

import com.querydsl.core.types.Predicate
import com.querydsl.core.types.QBean
import com.querydsl.jpa.JPQLQuery
import com.querydsl.jpa.impl.JPAQuery
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.Repository


//TODO null 가능으로 대부분되어 있으나 검토 후 조정 필요
@NoRepositoryBean
interface BaseQuerydslPredicateExecutor<DOMAIN, ID> : Repository<DOMAIN, ID> {

    fun from(): JPQLQuery<DOMAIN>

    /**
     * 페이징 - 자동 카운트 쿼리
     */
    fun <DTO> getPagingList(
        pageable: Pageable, projection: QBean<DTO>, query: JPAQuery<DOMAIN>
    ): Page<DTO>

    /**
     * 페이징 - 자동 카운트 쿼리
     */
    fun <DTO> getPagingList(
        pageable: Pageable, projection: QBean<DTO>, query: JPQLQuery<DOMAIN>
    ): Page<DTO>


    /**
     * 페이징 - 카운트 쿼리 별도 작성
     */
    fun <DTO> getPagingList(
        pageable: Pageable, projection: QBean<DTO>, contentQuery: JPQLQuery<DOMAIN>, countQuery: JPQLQuery<DOMAIN>
    ): Page<DTO>


    fun count(): Long

    fun count(predicate: Predicate?): Long

    fun count(query: JPQLQuery<DOMAIN>): Long

    fun exists(predicate: Predicate?): Boolean
}