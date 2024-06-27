package jonjal.temp.crawling.entity

import jonjal.temp.common.entity.BaseEntity
import jonjal.temp.crawling.dto.AssemblyMemberCurrentHistoryRowData
import jakarta.persistence.*

// 현재 국회의원 이력

@Entity
@Table(
    name = "TB_ASSEMBLY_MEMBER_CURRENT_HISTORY",
    indexes = [
        Index(name = "IDX_ASSEMBLY_MEMBER_CURRENT_HISTORY_의원이름한글", columnList = "의원이름한글", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_CURRENT_HISTORY_의원이력", columnList = "의원이력", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_CURRENT_HISTORY_국회의원코드", columnList = "국회의원코드", unique = false),
    ]
)
class AssemblyMemberCurrentHistory(
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASSEMBLY_MEMBER_CURRENT_HISTORY_ID")
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
    @Column(name = "대수코드", length = 10)
    var 대수코드: String? = "",
    @Column(name = "대수", length = 10)
    var 대수: String? = "",

    ) : BaseEntity() {
    fun update(dto: AssemblyMemberCurrentHistoryRowData): AssemblyMemberCurrentHistory{
        this.의원이름한글 = dto.의원이름한글
        this.의원이름한자 = dto.의원이름한자
        this.활동기간 = dto.활동기간
        this.의원이력 = dto.의원이력
        this.국회의원코드 = dto.국회의원코드
        this.대수코드 = dto.대수코드
        this.대수 = dto.대수
        return this
    }

    companion object {
        fun create(dto: AssemblyMemberCurrentHistoryRowData): AssemblyMemberCurrentHistory {
            return AssemblyMemberCurrentHistory(
                의원이름한글 = dto.의원이름한글,
                의원이름한자 = dto.의원이름한자,
                활동기간 = dto.활동기간,
                의원이력 = dto.의원이력,
                국회의원코드 = dto.국회의원코드,
                대수코드 = dto.대수코드,
                대수 = dto.대수
            )
        }
    }
}