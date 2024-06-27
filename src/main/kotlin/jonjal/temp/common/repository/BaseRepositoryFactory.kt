package jonjal.temp.common.repository

import jakarta.persistence.EntityManager
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory
import org.springframework.data.repository.core.RepositoryMetadata
import org.springframework.data.repository.core.support.RepositoryComposition.RepositoryFragments
import org.springframework.data.repository.core.support.RepositoryFragment
import java.io.Serializable

class BaseRepositoryFactory(private val entityManager: EntityManager) : JpaRepositoryFactory(
    entityManager
) {
    override fun getRepositoryFragments(metadata: RepositoryMetadata): RepositoryFragments {
        var fragments = super.getRepositoryFragments(metadata)
        if (BaseQuerydslPredicateExecutor::class.java.isAssignableFrom(
                metadata.repositoryInterface
            )
        ) {
            val entityInformation: JpaEntityInformation<*, Serializable> = getEntityInformation(metadata.domainType)
            val queryableFragment: Any = instantiateClass(
                BaseQuerydslPredicateExecutorImpl::class.java, entityInformation, entityManager
            )
            fragments = fragments.append(RepositoryFragment.implemented(queryableFragment))
        }
        return fragments
    }
}