package jonjal.temp.crawling.entity

import jonjal.temp.common.entity.BaseEntity
import jonjal.temp.crawling.dto.AssemblyMemberBillRowData
import jakarta.persistence.*
import java.time.LocalDate

//발의 법률안
@Entity
@Table(
    name = "TB_ASSEMBLY_MEMBER_BILL",
    indexes = [
        Index(name = "IDX_ASSEMBLY_MEMBER_BILL_의안ID", columnList = "의안ID", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_BILL_의안번호", columnList = "의안번호", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_BILL_법률안명", columnList = "법률안명", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_BILL_대표발의자", columnList = "대표발의자", unique = false),
        Index(name = "IDX_ASSEMBLY_MEMBER_BILL_공동발의자", columnList = "공동발의자", unique = false),
    ]
)
class AssemblyMemberBill(
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASSEMBLY_MEMBER_BILL_ID")
    var id: Long? = null,

    @Column(name = "의안ID", length = 50)
    var 의안ID: String? = "",
    @Column(name = "의안번호", length = 10)
    var 의안번호: String? = "",
    @Column(name = "법률안명", length = 500)
    var 법률안명: String? = "",
    @Column(name = "소관위원회", length = 100)
    var 소관위원회: String? = "",
    @Column(name = "제안일")
    var 제안일: LocalDate? = null,
    @Column(name = "본회의심의결과", length = 30)
    var 본회의심의결과: String? = "",
    @Column(name = "대수", length = 10)
    var 대수: String? = "",
    @Column(name = "상세페이지", length = 300)
    var 상세페이지: String? = "",
    @Column(name = "제안자", length = 100)
    var 제안자: String? = "",
    @Column(name = "제안자목록링크", length = 300)
    var 제안자목록링크: String? = "",
    @Column(name = "법사위처리일")
    var 법사위처리일: LocalDate? = null,
    @Column(name = "법사위상정일")
    var 법사위상정일: LocalDate? = null,
    @Column(name = "법사위회부일")
    var 법사위회부일: LocalDate? = null,
    @Column(name = "소관위처리결과", length = 100)
    var 소관위처리결과: String? = "",
    @Column(name = "소관위처리일")
    var 소관위처리일: LocalDate? = null,
    @Column(name = "소관위상정일")
    var 소관위상정일: LocalDate? = null,
    @Column(name = "소관위회부일")
    var 소관위회부일: LocalDate? = null,
    @Column(name = "의결일")
    var 의결일: LocalDate? = null,
    @Column(name = "소관위원회ID", length = 100)
    var 소관위원회ID: String? = "",
    @Column(name = "공동발의자", columnDefinition = "TEXT")
    var 공동발의자: String? = "",
    @Column(name = "법사위처리결과", length = 50)
    var 법사위처리결과: String? = "",
    @Column(name = "대표발의자", length = 100)
    var 대표발의자: String? = "",

    ) : BaseEntity() {
    fun update(dto: AssemblyMemberBillRowData): AssemblyMemberBill? {
        this.의안ID = dto.의안ID
        this.의안번호 = dto.의안번호
        this.법률안명 = dto.법률안명
        this.소관위원회 = dto.소관위원회
        this.제안일 = dto.제안일
        this.본회의심의결과 = dto.본회의심의결과
        this.대수 = dto.대수
        this.상세페이지 = dto.상세페이지
        this.제안자 = dto.제안자
        this.제안자목록링크 = dto.제안자목록링크
        this.법사위처리일 = dto.법사위처리일
        this.법사위상정일 = dto.법사위상정일
        this.법사위회부일 = dto.법사위회부일
        this.소관위처리결과 = dto.소관위처리결과
        this.소관위처리일 = dto.소관위처리일
        this.소관위상정일 = dto.소관위상정일
        this.소관위회부일 = dto.소관위회부일
        this.의결일 = dto.의결일
        this.소관위원회ID = dto.소관위원회ID
        this.공동발의자 = dto.공동발의자
        this.법사위처리결과 = dto.법사위처리결과
        this.대표발의자 = dto.대표발의자
        return this
    }


    companion object {
        fun create(dto: AssemblyMemberBillRowData): AssemblyMemberBill {
            return AssemblyMemberBill(
                의안ID = dto.의안ID,
                의안번호 = dto.의안번호,
                법률안명 = dto.법률안명,
                소관위원회 = dto.소관위원회,
                제안일 = dto.제안일,
                본회의심의결과 = dto.본회의심의결과,
                대수 = dto.대수,
                상세페이지 = dto.상세페이지,
                제안자 = dto.제안자,
                제안자목록링크 = dto.제안자목록링크,
                법사위처리일 = dto.법사위처리일,
                법사위상정일 = dto.법사위상정일,
                법사위회부일 = dto.법사위회부일,
                소관위처리결과 = dto.소관위처리결과,
                소관위처리일 = dto.소관위처리일,
                소관위상정일 = dto.소관위상정일,
                소관위회부일 = dto.소관위회부일,
                의결일 = dto.의결일,
                소관위원회ID = dto.소관위원회ID,
                공동발의자 = dto.공동발의자,
                법사위처리결과 = dto.법사위처리결과,
                대표발의자 = dto.대표발의자,
            )
        }
    }
}