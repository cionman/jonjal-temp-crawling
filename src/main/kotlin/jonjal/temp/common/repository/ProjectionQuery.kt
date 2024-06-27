package jonjal.temp.common.repository

import com.querydsl.jpa.JPQLQuery

fun interface ProjectionQuery<DOMAIN> {
    fun apply(query: JPQLQuery<DOMAIN>): JPQLQuery<DOMAIN>
}