package jonjal.temp.crawling.entity

//본회의 출결

import jonjal.temp.common.entity.BaseEntity
import jonjal.temp.crawling.dto.AssemblyGeneralAttendenceDto
import jakarta.persistence.*

@Entity
@Table(
    name = "TB_ASSEMBLY_GENERAL_ATTENDENCE",
    indexes = [
        Index(name = "IDX_ASSEMBLY_GENERAL_ATTENDENCE_국회의원코드", columnList = "국회의원코드", unique = false),
    ]
)
class AssemblyGeneralAttendence(
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASSEMBLY_GENERAL_ATTENDENCE_ID")
    var id: Long? = null,

    /**
     * 대수
     */
    @Column(name = "대수", length = 10, nullable = false)
    var 대수: String = "",

    /**
     * 이름
     */
    @Column(name = "이름", length = 100)
    var 이름: String? = "",

    /**
     * 정당명
     */
    @Column(name = "정당명", length = 100)
    var 정당명: String? = "",

    @Column(name = "회기", length = 20, nullable = false)
    var 회기: String? = "",

    @Column(name = "회기타입", length = 5)
    var 회기타입: String? = "",

    /**
     * 회의일수
     */
    @Column(name = "회의일수", nullable = false)
    var 회의일수: Int? = null,

    /**
     * 출석
     */
    @Column(name = "출석", nullable = false)
    var 출석: Int? = null,

    /**
     * 결석
     */
    @Column(name = "결석", nullable = false)
    var 결석: Int? = null,

    /**
     * 청가
     */
    @Column(name = "청가", nullable = false)
    var 청가: Int? = null,

    /**
     * 출장
     */
    @Column(name = "출장", nullable = false)
    var 출장: Int? = null,

    /**
     * 결석신고서
     */
    @Column(name = "결석신고서", nullable = false)
    var 결석신고서: Int? = null,

    @Column(name = "국회의원코드", length = 30)
    var 국회의원코드: String?,

    ) : BaseEntity() {


    companion object {
        fun create(dto: AssemblyGeneralAttendenceDto): AssemblyGeneralAttendence {
            return AssemblyGeneralAttendence(
                대수 = dto.대수,
                이름 = dto.이름,
                정당명 = dto.정당명,
                회기 = dto.회기,
                회기타입 = dto.회기타입,
                회의일수 = dto.회의일수,
                출석 = dto.출석,
                결석 = dto.결석,
                청가 = dto.청가,
                출장 = dto.출장,
                결석신고서 = dto.결석신고서,
                국회의원코드 = dto.국회의원코드
            )
        }
    }
}