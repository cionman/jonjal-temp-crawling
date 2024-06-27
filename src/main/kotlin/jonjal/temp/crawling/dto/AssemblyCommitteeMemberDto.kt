package jonjal.temp.crawling.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AssemblyCommitteeMemberDto(

    /**
     * ID
     */
    var id: Long? = null,

    @JsonProperty("nktulghcadyhmiqxi")
    var result: List<AssemblyCommitteeMemberHeadAndRow>

)


data class AssemblyCommitteeMemberHeadAndRow(
    @JsonProperty("head")
    var head: List<AssemblyCommitteeMemberHeadItem>? = null,

    @JsonProperty("row")
    var row: List<AssemblyCommitteeMemberRowData>? = null

)

data class AssemblyCommitteeMemberHeadItem(
    @JsonProperty("list_total_count")
    val listTotalCount: Int? = null,

    @JsonProperty("RESULT")
    val result: AssemblyCommitteeMemberResult? = null
)

data class AssemblyCommitteeMemberResult(
    @JsonProperty("CODE")
    val code: String,

    @JsonProperty("MESSAGE")
    val message: String
)

data class AssemblyCommitteeMemberRowData(

    @JsonProperty("DEPT_CD")
    var 위원회코드: String = "",

    /**
     * 위원회명
     */
    @JsonProperty("DEPT_NM")
    var 위원회명: String = "",

    /**
     * 구성
     */
    @JsonProperty("JOB_RES_NM")
    var 구성: String = "",

    /**
     *
     */
    @JsonProperty("HG_NM")
    var 이름: String = "",

    /**
     * 한자명
     */
    @JsonProperty("HJ_NM")
    var 한자명: String? = "",

    @JsonProperty("MONA_CD")
    var 국회의원코드: String? = "",
)