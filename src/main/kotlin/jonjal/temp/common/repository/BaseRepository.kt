package jonjal.temp.common.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseRepository<DOMAIN, ID> : JpaRepository<DOMAIN, ID>, BaseQuerydslPredicateExecutor<DOMAIN, ID>