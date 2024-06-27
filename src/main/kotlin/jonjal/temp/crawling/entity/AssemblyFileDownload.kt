package jonjal.temp.crawling.entity

//파일 다운로드 기록

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(
    name = "TB_ASSEMBLY_FILE_DOWNLOAD",
)
class AssemblyFileDownload(

    /**
     * 파일명
     */
    @Id
    @Column(name = "FILE_NAME", length = 300)
    var fileName: String = "",

    @CreationTimestamp
    @Column(name = "CREATED_DATE", updatable = false)
    var createdDate: LocalDateTime? = null
) {
    companion object {
        fun create(fileName: String): AssemblyFileDownload {
            return AssemblyFileDownload(
                fileName = fileName,
            )
        }
    }
}