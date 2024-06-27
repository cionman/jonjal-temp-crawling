package jonjal.temp.crawling.entity

import jonjal.temp.common.entity.BaseEntity
import jonjal.temp.crawling.dto.AssemblyMemberPastCommitteeRowData
import jakarta.persistence.*

//과거 상임위 이력

@Entity
@Table(
    name = "TB_ASSEMBLY_MEMBER_PAST_COMMITTEE",
    indexes = [
        Index(name = "IDX_ASSEMBLY_MEMBER_PAST_COMMITTEE_의원이름한글", columnList = "의원이름한글", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_PAST_COMMITTEE_위원회", columnList = "위원회", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_PAST_COMMITTEE_국회의원코드", columnList = "국회의원코드", unique = false),
    ]
)
class AssemblyMemberPastCommittee(
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASSEMBLY_MEMBER_PAST_COMMITTEE_ID")
    var id: Long? = null,
    @Column(name = "구분코드", length = 10)
    var 구분코드: String? = "",
    @Column(name = "구분", length = 100)
    var 구분: String? = "",
    @Column(name = "의원이름한글", length = 100)
    var 의원이름한글: String? = "",
    @Column(name = "의원이름한자", length = 100)
    var 의원이름한자: String? = "",
    @Column(name = "활동기간", length = 50)
    var 활동기간: String? = "",
    @Column(name = "위원회", length = 300)
    var 위원회경력: String? = "",
    @Column(name = "국회의원코드", length = 30)
    var 국회의원코드: String? = "",
    @Column(name = "경력대수코드", length = 10)
    var 경력대수코드: String? = "",
    @Column(name = "경력대수", length = 10)
    var 경력대수: String? = "",

    ) : BaseEntity() {

    fun update(dto: AssemblyMemberPastCommitteeRowData): AssemblyMemberPastCommittee {
        this.구분코드 = dto.구분코드
        this.구분 = dto.구분
        this.의원이름한글 = dto.의원이름한글
        this.의원이름한자 = dto.의원이름한자
        this.활동기간 = dto.활동기간
        this.위원회경력 = dto.위원회경력
        this.국회의원코드 = dto.국회의원코드
        this.경력대수코드 = dto.경력대수코드
        this.경력대수 = dto.경력대수
        return this
    }

    companion object {
        fun create(dto: AssemblyMemberPastCommitteeRowData): AssemblyMemberPastCommittee {
            return AssemblyMemberPastCommittee(
                구분코드 = dto.구분코드,
                구분 = dto.구분,
                의원이름한글 = dto.의원이름한글,
                의원이름한자 = dto.의원이름한자,
                활동기간 = dto.활동기간,
                위원회경력 = dto.위원회경력,
                국회의원코드 = dto.국회의원코드,
                경력대수코드 = dto.경력대수코드,
                경력대수 = dto.경력대수
            )
        }
    }
}