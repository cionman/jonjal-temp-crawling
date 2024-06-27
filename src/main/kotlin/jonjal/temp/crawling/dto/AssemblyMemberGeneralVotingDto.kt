package jonjal.temp.crawling.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class AssemblyMemberGeneralVotingDto(

    /**
     * ID
     */
    var id: Long? = null,

    @JsonProperty("nojepdqqaweusdfbi")
    var result: List<AssemblyMemberGeneralVotingHeadAndRow>

)


data class AssemblyMemberGeneralVotingHeadAndRow(
    @JsonProperty("head")
    var head: List<AssemblyMemberGeneralVotingHeadItem>? = null,

    @JsonProperty("row")
    var row: List<AssemblyMemberGeneralVotingRowData>? = null

)

data class AssemblyMemberGeneralVotingHeadItem(
    @JsonProperty("list_total_count")
    val listTotalCount: Int? = null,

    @JsonProperty("RESULT")
    val result: AssemblyMemberGeneralVotingResult? = null
)

data class AssemblyMemberGeneralVotingResult(
    @JsonProperty("CODE")
    val code: String,

    @JsonProperty("MESSAGE")
    val message: String
)

data class AssemblyMemberGeneralVotingRowData(

    /**
     * 이름
     */
    @JsonProperty("HG_NM")
    var 의원명: String? = "",


    /**
     * 한자명
     */
    @JsonProperty("HJ_NM")
    var 한자명: String? = "",


    /**
     * 정당명
     */
    @JsonProperty("POLY_NM")
    var 정당명: String? = "",

    /**
     * 선거구
     */
    @JsonProperty("ORIG_NM")
    var 선거구: String? = "",


    /**
     * 의원번호
     */
    @JsonProperty("MEMBER_NO")
    var 의원번호: String = "",

    @JsonProperty("POLY_CD")
    var 소속정당코드: String? = "",

    @JsonFormat(pattern = "yyyyMMdd HHmmss")
    @JsonProperty("VOTE_DATE")
    var 의결일자: LocalDate? = null,


    @JsonProperty("BILL_NO")
    var 의안번호: String? = "",
    @JsonProperty("BILL_NAME")
    var 의안명: String? = "",
    @JsonProperty("BILL_ID")
    var 의안ID: String? = "",

    @JsonProperty("LAW_TITLE")
    var 법률명: String? = "",

    @JsonProperty("CURR_COMMITTEE")
    var 소관위원회: String? = "",

    @JsonProperty("CURR_COMMITTEE_ID")
    var 소관위코드: String? = "",

    /**
     * 표결결과
     */
    @JsonProperty("RESULT_VOTE_MOD")
    var 표결결과: String = "",

    /**
     * 회기
     */
    @JsonProperty("SESSION_CD")
    var 회기: String = "",

    /**
     * 차수
     */
    @JsonProperty("CURRENTS_CD")
    var 차수: String = "",

    @JsonProperty("AGE")
    var 대: String? = "",

    @JsonProperty("MONA_CD")
    var 국회의원코드: String? = "",
)