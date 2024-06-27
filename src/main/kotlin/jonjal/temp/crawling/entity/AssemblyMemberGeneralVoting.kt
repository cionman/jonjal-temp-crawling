package jonjal.temp.crawling.entity

import jonjal.temp.common.entity.BaseEntity
import jonjal.temp.crawling.dto.AssemblyMemberGeneralVotingRowData
import jakarta.persistence.*
import java.time.LocalDate

// 본회의 표결정보
@Entity
@Table(
    name = "TB_ASSEMBLY_MEMBER_GENERAL_VOTING",
    indexes = [
        Index(name = "IDX_ASSEMBLY_MEMBER_GENERAL_VOTING_법률명", columnList = "법률명", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_GENERAL_VOTING_의안ID", columnList = "의안ID", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_GENERAL_VOTING_의안명", columnList = "의안명", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_GENERAL_VOTING_의안번호", columnList = "의안번호", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_GENERAL_VOTING_국회의원코드", columnList = "국회의원코드", unique = false),
    ]
)
class AssemblyMemberGeneralVoting(
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASSEMBLY_MEMBER_GENERAL_VOTING_ID")
    var id: Long? = null,


    /**
     * 이름
     */
    @Column(name = "의원명", length = 100)
    var 의원명: String? = "",


    /**
     * 한자명
     */
    @Column(name = "한자명", length = 100)
    var 한자명: String? = "",


    /**
     * 정당명
     */
    @Column(name = "정당명", length = 100)
    var 정당명: String? = "",

    /**
     * 선거구
     */
    @Column(name = "선거구", length = 200)
    var 선거구: String? = "",


    /**
     * 의원번호
     */
    @Column(name = "의원번호", length = 30)
    var 의원번호: String = "",

    @Column(name = "소속정당코드", length = 10)
    var 소속정당코드: String? = "",

    @Column(name = "의결일자", length = 10)
    var 의결일자: LocalDate? = null,


    @Column(name = "의안번호", length = 10)
    var 의안번호: String? = "",
    @Column(name = "의안명", length = 500)
    var 의안명: String? = "",
    @Column(name = "의안ID", length = 50)
    var 의안ID: String? = "",

    @Column(name = "법률명", length = 500)
    var 법률명: String? = "",

    @Column(name = "소관위원회", length = 100)
    var 소관위원회: String? = "",

    @Column(name = "소관위코드", length = 100)
    var 소관위코드: String? = "",

    /**
     * 표결결과
     */
    @Column(name = "표결결과", length = 10)
    var 표결결과: String = "",

    /**
     * 회기
     */
    @Column(name = "회기", length = 10)
    var 회기: String = "",

    /**
     * 차수
     */
    @Column(name = "차수", length = 5)
    var 차수: String = "",

    @Column(name = "대", length = 5)
    var 대: String? = "",


    @Column(name = "국회의원코드", length = 30)
    var 국회의원코드: String? = "",


    ) : BaseEntity() {
    fun update(dto: AssemblyMemberGeneralVotingRowData): AssemblyMemberGeneralVoting? {

        this.의원명 = dto.의원명
        this.한자명 = dto.한자명
        this.정당명 = dto.정당명
        this.선거구 = dto.선거구
        this.의원번호 = dto.의원번호
        this.소속정당코드 = dto.소속정당코드
        this.의결일자 = dto.의결일자
        this.의안번호 = dto.의안번호
        this.의안명 = dto.의안명
        this.의안ID = dto.의안ID
        this.법률명 = dto.법률명
        this.소관위원회 = dto.소관위원회
        this.소관위코드 = dto.소관위코드
        this.표결결과 = dto.표결결과
        this.회기 = dto.회기
        this.차수 = dto.차수
        this.대 = dto.대
        this.국회의원코드 = dto.국회의원코드

        return this
    }


    companion object {
        fun create(dto: AssemblyMemberGeneralVotingRowData): AssemblyMemberGeneralVoting {
            return AssemblyMemberGeneralVoting(
                의원명 = dto.의원명,
                한자명 = dto.한자명,
                정당명 = dto.정당명,
                선거구 = dto.선거구,
                의원번호 = dto.의원번호,
                소속정당코드 = dto.소속정당코드,
                의결일자 = dto.의결일자,
                의안번호 = dto.의안번호,
                의안명 = dto.의안명,
                의안ID = dto.의안ID,
                법률명 = dto.법률명,
                소관위원회 = dto.소관위원회,
                소관위코드 = dto.소관위코드,
                표결결과 = dto.표결결과,
                회기 = dto.회기,
                차수 = dto.차수,
                대 = dto.대,
                국회의원코드 = dto.국회의원코드,
            )
        }
    }
}