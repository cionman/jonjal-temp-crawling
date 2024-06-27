package jonjal.temp.crawling.entity


import jonjal.temp.common.entity.BaseEntity
import jonjal.temp.crawling.dto.AssemblyTermInfoRowData
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(
    name = "TB_ASSEMBLY_TERM_INFO"
)
class AssemblyTermInfo(
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASSEMBLY_TERM_INFO_ID")
    var id: Long? = null,

    /**
     * 대수
     */
    @Column(name = "대수", length = 10, nullable = false)
    var 대수: String = "",

    @Column(name = "대별선거구분", length = 200)
    var 대별선거구분: String? = "",

    @Column(name = "선거일")
    var 선거일: LocalDate? = null,

    @Column(name = "의원정수")
    var 의원정수: Int? = null,

    @Column(name = "임기시작")
    var 임기시작: LocalDate? = null,

    @Column(name = "임기종료")
    var 임기종료: LocalDate? = null,

    @Column(name = "임기기간", length = 50)
    var 임기기간: String? = "",

    @Column(name = "비고", length = 500)
    var 비고: String? = "",
) : BaseEntity() {

    fun update(dto: AssemblyTermInfoRowData): AssemblyTermInfo {
        this.대수 = dto.대수
        this.대별선거구분 = dto.대별선거구분
        this.선거일 = dto.선거일
        this.의원정수 = dto.의원정수
        this.임기시작 = dto.임기시작
        this.임기종료 = dto.임기종료
        this.임기기간 = dto.임기기간
        this.비고 = dto.비고
        return this
    }

    companion object {
        fun create(dto: AssemblyTermInfoRowData): AssemblyTermInfo {
            return AssemblyTermInfo(
                대수 = dto.대수,
                대별선거구분 = dto.대별선거구분,
                선거일 = dto.선거일,
                의원정수 = dto.의원정수,
                임기시작 = dto.임기시작,
                임기종료 = dto.임기종료,
                임기기간 = dto.임기기간,
                비고 = dto.비고,
            )
        }
    }
}