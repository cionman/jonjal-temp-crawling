package jonjal.temp.common.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {
    @CreationTimestamp
    @Column(name = "CREATED_DATE", updatable = false)
    var createdDate: LocalDateTime? = null

    @UpdateTimestamp
    @Column(name = "MODIFIED_DATE")
    var modifiedDate: LocalDateTime? = null
}