package jonjal.temp.crawling

import jonjal.temp.common.repository.BaseRepositoryFactoryBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean::class)
class JonjalTempCrawlingApplication

fun main(args: Array<String>) {
    runApplication<JonjalTempCrawlingApplication>(*args)
}
