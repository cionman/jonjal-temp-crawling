package jonjal.temp.crawling.entity

//현재 국회의원 기본정보

import jonjal.temp.common.entity.BaseEntity
import jonjal.temp.crawling.dto.AssemblyMemberCurrentRowData
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(
    name = "TB_ASSEMBLY_MEMBER_CURRENT",
    indexes = [
        Index(name = "IDX_ASSEMBLY_MEMBER_CURRENT_이름", columnList = "이름", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_CURRENT_영문명칭", columnList = "영문명칭", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_CURRENT_정당명", columnList = "정당명", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_CURRENT_선거구", columnList = "선거구", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_CURRENT_국회의원코드", columnList = "국회의원코드", unique = false),
    ]
)
class AssemblyMemberCurrent(
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASSEMBLY_MEMBER_CURRENT_ID")
    var id: Long? = null,


    /**
     * 이름
     */
    @Column(name = "이름", length = 100)
    var 이름: String? = "",


    /**
     * 한자명
     */
    @Column(name = "한자명", length = 100)
    var 한자명: String? = "",

    /**
     *
     */
    @Column(name = "영문명칭", length = 150)
    var 영문명칭: String? = "",

    /**
     * 음양력
     */
    @Column(name = "음양력", length = 5)
    var 음양력: String? = "",

    /**
     * 생년월일
     */
    @Column(name = "생년월일")
    var 생년월일: LocalDate? = null,


    /**
     * 직책명
     */
    @Column(name = "직책명", length = 100)
    var 직책명: String? = "",

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


    @Column(name = "선거구구분", length = 100)
    var 선거구구분: String? = "",

    @Column(name = "대표위원회", length = 100)
    var 대표위원회: String? = "",

    @Column(name = "소속위원회목록", columnDefinition = "TEXT")
    var 소속위원회목록: String? = "",

    @Column(name = "재선", length = 10)
    var 재선: String? = "",

    @Column(name = "당선", length = 300)
    var 당선: String? = "",


    @Column(name = "성별", length = 5)
    var 성별: String? = "",

    @Column(name = "전화번호", length = 100)
    var 전화번호: String? = "",

    @Column(name = "이메일", length = 100)
    var 이메일: String? = "",

    @Column(name = "홈페이지", length = 200)
    var 홈페이지: String? = "",

    @Column(name = "보좌관", length = 300)
    var 보좌관: String? = "",

    @Column(name = "비서관", length = 300)
    var 비서관: String? = "",

    @Column(name = "비서", length = 500)
    var 비서: String? = "",

    @Column(name = "국회의원코드", length = 30)
    var 국회의원코드: String? = "",

    @Column(name = "약력", columnDefinition = "TEXT")
    var 약력: String? = "",

    @Column(name = "사무실호실", length = 100)
    var 사무실호실: String? = "",

    ) : BaseEntity() {
    fun update(dto: AssemblyMemberCurrentRowData): AssemblyMemberCurrent? {
        this.이름 = dto.이름
        this.한자명 = dto.한자명
        this.영문명칭 = dto.영문명칭
        this.음양력 = dto.음양력
        this.생년월일 = dto.생년월일
        this.직책명 = dto.직책명
        this.정당명 = dto.정당명
        this.선거구 = dto.선거구
        this.선거구구분 = dto.선거구구분
        this.대표위원회 = dto.대표위원회
        this.소속위원회목록 = dto.소속위원회목록
        this.재선 = dto.재선
        this.당선 = dto.당선
        this.성별 = dto.성별
        this.전화번호 = dto.전화번호
        this.이메일 = dto.이메일
        this.홈페이지 = dto.홈페이지
        this.보좌관 = dto.보좌관
        this.비서관 = dto.비서관
        this.비서 = dto.비서
        this.국회의원코드 = dto.국회의원코드
        this.약력 = dto.약력
        this.사무실호실 = dto.사무실호실
        return this
    }


    companion object {
        fun create(dto: AssemblyMemberCurrentRowData): AssemblyMemberCurrent {
            return AssemblyMemberCurrent(
                이름 = dto.이름,
                한자명 = dto.한자명,
                영문명칭 = dto.영문명칭,
                음양력 = dto.음양력,
                생년월일 = dto.생년월일,
                직책명 = dto.직책명,
                정당명 = dto.정당명,
                선거구 = dto.선거구,
                선거구구분 = dto.선거구구분,
                대표위원회 = dto.대표위원회,
                소속위원회목록 = dto.소속위원회목록,
                재선 = dto.재선,
                당선 = dto.당선,
                성별 = dto.성별,
                전화번호 = dto.전화번호,
                이메일 = dto.이메일,
                홈페이지 = dto.홈페이지,
                보좌관 = dto.보좌관,
                비서관 = dto.비서관,
                비서 = dto.비서,
                국회의원코드 = dto.국회의원코드,
                약력 = dto.약력,
                사무실호실 = dto.사무실호실,
            )
        }
    }
}