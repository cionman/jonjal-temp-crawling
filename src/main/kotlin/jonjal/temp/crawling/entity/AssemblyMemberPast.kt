package jonjal.temp.crawling.entity

import jonjal.temp.common.entity.BaseEntity
import jonjal.temp.crawling.dto.AssemblyMemberPastRowData
import jakarta.persistence.*
import java.time.LocalDate

// 과거 국회의원 기본정보

@Entity
@Table(
    name = "TB_ASSEMBLY_MEMBER_PAST",
    indexes = [
        Index(name = "IDX_ASSEMBLY_MEMBER_PAST_이름", columnList = "이름", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_PAST_영문명칭", columnList = "영문명칭", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_PAST_정당명", columnList = "정당명", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_PAST_선거구", columnList = "선거구", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_PAST_국회의원코드", columnList = "국회의원코드", unique = false),
    ]
)
class AssemblyMemberPast(
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASSEMBLY_MEMBER_PAST_ID")
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

    @Column(name = "재선", length = 10)
    var 재선: String? = "",

    @Column(name = "당선", length = 300)
    var 당선: String? = "",

    @Column(name = "성별", length = 5)
    var 성별: String? = "",

    @Column(name = "대별코드", length = 10)
    var 대별코드: String? = "",
    @Column(name = "대", length = 10)
    var 대: String? = "",

    @Column(name = "국회의원코드", length = 30)
    var 국회의원코드: String? = "",


    ) : BaseEntity() {
    fun update(dto: AssemblyMemberPastRowData): AssemblyMemberPast? {
        this.이름 = dto.이름
        this.한자명 = dto.한자명
        this.영문명칭 = dto.영문명칭
        this.음양력 = dto.음양력
        this.생년월일 = dto.생년월일
        this.정당명 = dto.정당명
        this.선거구 = dto.선거구
        this.선거구구분 = dto.선거구구분
        this.대 = dto.대
        this.대별코드 = dto.대별코드
        this.재선 = dto.재선
        this.당선 = dto.당선
        this.성별 = dto.성별
        this.국회의원코드 = dto.국회의원코드
        return this
    }


    companion object {
        fun create(dto: AssemblyMemberPastRowData): AssemblyMemberPast {
            return AssemblyMemberPast(
                이름 = dto.이름,
                한자명 = dto.한자명,
                영문명칭 = dto.영문명칭,
                음양력 = dto.음양력,
                생년월일 = dto.생년월일,
                정당명 = dto.정당명,
                선거구 = dto.선거구,
                선거구구분 = dto.선거구구분,
                대 = dto.대,
                대별코드 = dto.대별코드,
                재선 = dto.재선,
                당선 = dto.당선,
                성별 = dto.성별,
                국회의원코드 = dto.국회의원코드,
            )
        }
    }
}