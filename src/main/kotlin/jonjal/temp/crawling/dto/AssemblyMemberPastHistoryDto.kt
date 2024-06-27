package jonjal.temp.crawling.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AssemblyMemberPastHistoryDto(

    /**
     * ID
     */
    var id: Long? = null,

    @JsonProperty("nfzegpkvaclgtscxt")
    var result: List<AssemblyMemberPastHistoryHeadAndRow>

)


data class AssemblyMemberPastHistoryHeadAndRow(
    @JsonProperty("head")
    var head: List<AssemblyMemberPastHistoryHeadItem>? = null,

    @JsonProperty("row")
    var row: List<AssemblyMemberPastHistoryRowData>? = null

)

data class AssemblyMemberPastHistoryHeadItem(
    @JsonProperty("list_total_count")
    val listTotalCount: Int? = null,

    @JsonProperty("RESULT")
    val result: AssemblyMemberPastHistoryResult? = null
)

data class AssemblyMemberPastHistoryResult(
    @JsonProperty("CODE")
    val code: String,

    @JsonProperty("MESSAGE")
    val message: String
)

data class AssemblyMemberPastHistoryRowData(
    @JsonProperty("HG_NM")
    var 의원이름한글: String? = "",
    @JsonProperty("HJ_NM")
    var 의원이름한자: String? = "",
    @JsonProperty("FRTO_DATE")
    var 활동기간: String? = "",
    @JsonProperty("PROFILE_SJ")
    var 의원이력: String? = "",
    @JsonProperty("MONA_CD")
    var 국회의원코드: String? = "",
    @JsonProperty("PROFILE_UNIT_CD")
    var 경력대수코드: String? = "",
    @JsonProperty("PROFILE_UNIT_NM")
    var 경력대수: String? = "",
)