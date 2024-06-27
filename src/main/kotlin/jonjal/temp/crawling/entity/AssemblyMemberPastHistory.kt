package jonjal.temp.crawling.entity

import jonjal.temp.common.entity.BaseEntity
import jonjal.temp.crawling.dto.AssemblyMemberPastHistoryRowData
import jakarta.persistence.*

//과거 국회의원 이력

@Entity
@Table(
    name = "TB_ASSEMBLY_MEMBER_PAST_HISTORY",
    indexes = [
        Index(name = "IDX_ASSEMBLY_MEMBER_PAST_HISTORY_의원이름한글", columnList = "의원이름한글", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_PAST_HISTORY_의원이력", columnList = "의원이력", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_PAST_HISTORY_국회의원코드", columnList = "국회의원코드", unique = false),
    ]
)
class AssemblyMemberPastHistory(
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASSEMBLY_MEMBER_PAST_HISTORY_ID")
    var id: Long? = null,
    @Column(name = "의원이름한글", length = 100)
    var 의원이름한글: String? = "",
    @Column(name = "의원이름한자", length = 100)
    var 의원이름한자: String? = "",
    @Column(name = "활동기간", length = 50)
    var 활동기간: String? = "",
    @Column(name = "의원이력", length = 300)
    var 의원이력: String? = "",
    @Column(name = "국회의원코드", length = 30)
    var 국회의원코드: String? = "",
    @Column(name = "경력대수코드", length = 10)
    var 경력대수코드: String? = "",
    @Column(name = "경력대수", length = 10)
    var 경력대수: String? = "",

    ) : BaseEntity() {
    fun update(dto: AssemblyMemberPastHistoryRowData): AssemblyMemberPastHistory {
        this.의원이름한글 = dto.의원이름한글
        this.의원이름한자 = dto.의원이름한자
        this.활동기간 = dto.활동기간
        this.의원이력 = dto.의원이력
        this.국회의원코드 = dto.국회의원코드
        this.경력대수코드 = dto.경력대수코드
        this.경력대수 = dto.경력대수
        return this
    }

    companion object {
        fun create(dto: AssemblyMemberPastHistoryRowData): AssemblyMemberPastHistory {
            return AssemblyMemberPastHistory(
                의원이름한글 = dto.의원이름한글,
                의원이름한자 = dto.의원이름한자,
                활동기간 = dto.활동기간,
                의원이력 = dto.의원이력,
                국회의원코드 = dto.국회의원코드,
                경력대수코드 = dto.경력대수코드,
                경력대수 = dto.경력대수
            )
        }
    }
}