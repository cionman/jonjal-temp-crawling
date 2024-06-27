package jonjal.temp.common.repository

import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean
import org.springframework.data.repository.Repository
import org.springframework.data.repository.core.support.RepositoryFactorySupport

class BaseRepositoryFactoryBean<T : Repository<S, I>?, S, I>(repositoryInterface: Class<out T>?) :
    JpaRepositoryFactoryBean<T, S, I>(repositoryInterface!!) {
    override fun createRepositoryFactory(entityManager: EntityManager): RepositoryFactorySupport {
        return BaseRepositoryFactory(entityManager)
    }
}