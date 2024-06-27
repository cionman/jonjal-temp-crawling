package jonjal.temp.crawling.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class AssemblyMemberBillDto(

    /**
     * ID
     */
    var id: Long? = null,

    @JsonProperty("nzmimeepazxkubdpn")
    var result: List<AssemblyMemberBillHeadAndRow>

)


data class AssemblyMemberBillHeadAndRow(
    @JsonProperty("head")
    var head: List<AssemblyMemberBillHeadItem>? = null,

    @JsonProperty("row")
    var row: List<AssemblyMemberBillRowData>? = null

)

data class AssemblyMemberBillHeadItem(
    @JsonProperty("list_total_count")
    val listTotalCount: Int? = null,

    @JsonProperty("RESULT")
    val result: AssemblyMemberBillResult? = null
)

data class AssemblyMemberBillResult(
    @JsonProperty("CODE")
    val code: String,

    @JsonProperty("MESSAGE")
    val message: String
)

data class AssemblyMemberBillRowData(
    @JsonProperty("BILL_ID")
    var 의안ID: String? = "",
    @JsonProperty("BILL_NO")
    var 의안번호: String? = "",
    @JsonProperty("BILL_NAME")
    var 법률안명: String? = "",
    @JsonProperty("COMMITTEE")
    var 소관위원회: String? = "",
    @JsonProperty("PROPOSE_DT")
    var 제안일: LocalDate? = null,
    @JsonProperty("PROC_RESULT")
    var 본회의심의결과: String? = "",
    @JsonProperty("AGE")
    var 대수: String? = "",
    @JsonProperty("DETAIL_LINK")
    var 상세페이지: String? = "",
    @JsonProperty("PROPOSER")
    var 제안자: String? = "",
    @JsonProperty("MEMBER_LIST")
    var 제안자목록링크: String? = "",
    @JsonProperty("LAW_PROC_DT")
    var 법사위처리일: LocalDate? = null,
    @JsonProperty("LAW_PRESENT_DT")
    var 법사위상정일: LocalDate? = null,
    @JsonProperty("LAW_SUBMIT_DT")
    var 법사위회부일: LocalDate? = null,
    @JsonProperty("CMT_PROC_RESULT_CD")
    var 소관위처리결과: String? = "",
    @JsonProperty("CMT_PROC_DT")
    var 소관위처리일: LocalDate? = null,
    @JsonProperty("CMT_PRESENT_DT")
    private var _소관위상정일: String? = null,
    @JsonProperty("COMMITTEE_DT")
    var 소관위회부일: LocalDate? = null,
    @JsonProperty("PROC_DT")
    var 의결일: LocalDate? = null,
    @JsonProperty("COMMITTEE_ID")
    var 소관위원회ID: String? = "",
    @JsonProperty("PUBL_PROPOSER")
    var 공동발의자: String? = "",
    @JsonProperty("LAW_PROC_RESULT_CD")
    var 법사위처리결과: String? = "",
    @JsonProperty("RST_PROPOSER")
    var 대표발의자: String? = "",
) {
    var 소관위상정일: LocalDate? = null
        get() {
            try {
                return if (_소관위상정일 != null) LocalDate.parse(
                    _소관위상정일,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")
                ) else null
            } catch (e: Exception) {
                return if (_소관위상정일.equals("2003- 4-17")) LocalDate.of(2003, 4, 17) else throw e
            }
        }
}