package jonjal.temp.crawling.entity

import jonjal.temp.common.entity.BaseEntity
import jonjal.temp.crawling.dto.AssemblyMemberSnsRowData
import jakarta.persistence.*

//국회의원 SNS
@Entity
@Table(
    name = "TB_ASSEMBLY_MEMBER_SNS",
    indexes = [
        Index(name = "IDX_ASSEMBLY_MEMBER_SNS_이름", columnList = "이름", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_SNS_국회의원코드", columnList = "국회의원코드", unique = false),
    ]
)
class AssemblyMemberSns(
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASSEMBLY_MEMBER_SNS_ID")
    var id: Long? = null,
    @Column(name = "이름", length = 100)
    var 이름: String? = "",
    @Column(name = "트위터", length = 500)
    var 트위터: String? = "",
    @Column(name = "페이스북", length = 500)
    var 페이스북: String? = "",
    @Column(name = "유튜브", length = 500)
    var 유튜브: String? = "",
    @Column(name = "블로그", length = 500)
    var 블로그: String? = "",
    @Column(name = "국회의원코드", length = 30)
    var 국회의원코드: String? = "",

    ) : BaseEntity() {
    fun update(dto: AssemblyMemberSnsRowData): AssemblyMemberSns {
        this.이름 = dto.이름
        this.트위터 = dto.트위터
        this.페이스북 = dto.페이스북
        this.유튜브 = dto.유튜브
        this.블로그 = dto.블로그
        this.국회의원코드 = dto.국회의원코드
        return this
    }

    companion object {
        fun create(dto: AssemblyMemberSnsRowData): AssemblyMemberSns {
            return AssemblyMemberSns(
                이름 = dto.이름,
                트위터 = dto.트위터,
                페이스북 = dto.페이스북,
                유튜브 = dto.유튜브,
                블로그 = dto.블로그,
                국회의원코드 = dto.국회의원코드,
            )
        }
    }
}