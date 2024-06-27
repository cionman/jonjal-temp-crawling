package jonjal.temp.crawling.entity

//본회의 출결

import jonjal.temp.common.entity.BaseEntity
import jonjal.temp.crawling.dto.AssemblyCommitteeMemberRowData
import jakarta.persistence.*

@Entity
@Table(
    name = "TB_ASSEMBLY_COMMITTEE_MEMBER",
    indexes = [
        Index(name = "IDX_ASSEMBLY_COMMITTEE_MEMBER_이름한자명", columnList = "이름,한자명", unique = false),
        Index(name = "IDX_ASSEMBLY_COMMITTEE_MEMBER_국회의원코드", columnList = "국회의원코드", unique = false),
    ]
)
class AssemblyCommitteeMember(
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASSEMBLY_COMMITTEE_MEMBER_ID")
    var id: Long? = null,

    /**
     * 위원회코드
     */
    @Column(name = "위원회코드", length = 10)
    var 위원회코드: String = "",

    /**
     * 위원회명
     */
    @Column(name = "위원회명", length = 100)
    var 위원회명: String = "",

    /**
     * 구성
     */
    @Column(name = "구성", length = 10)
    var 구성: String = "",

    /**
     *
     */
    @Column(name = "이름", length = 100)
    var 이름: String = "",

    /**
     * 한자명
     */
    @Column(name = "한자명", length = 100)
    var 한자명: String? = "",

    @Column(name = "국회의원코드", length = 30)
    var 국회의원코드: String? = "",

    ) : BaseEntity() {

    fun update(dto: AssemblyCommitteeMemberRowData): AssemblyCommitteeMember {
        this.위원회코드 = dto.위원회코드
        this.위원회명 = dto.위원회명
        this.구성 = dto.구성
        this.이름 = dto.이름
        this.한자명 = dto.한자명
        this.국회의원코드 = dto.국회의원코드
        return this
    }

    companion object {

        fun create(dto: AssemblyCommitteeMemberRowData): AssemblyCommitteeMember {
            return AssemblyCommitteeMember(
                위원회코드 = dto.위원회코드,
                위원회명 = dto.위원회명,
                구성 = dto.구성,
                이름 = dto.이름,
                한자명 = dto.한자명,
                국회의원코드 = dto.국회의원코드,
            )
        }
    }
}