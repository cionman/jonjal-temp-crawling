package jonjal.temp.crawling.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AssemblyMemberCurrentHistoryDto(

    /**
     * ID
     */
    var id: Long? = null,

    @JsonProperty("nexgtxtmaamffofof")
    var result: List<AssemblyMemberCurrentHistoryHeadAndRow>

)


data class AssemblyMemberCurrentHistoryHeadAndRow(
    @JsonProperty("head")
    var head: List<AssemblyMemberCurrentHistoryHeadItem>? = null,

    @JsonProperty("row")
    var row: List<AssemblyMemberCurrentHistoryRowData>? = null

)

data class AssemblyMemberCurrentHistoryHeadItem(
    @JsonProperty("list_total_count")
    val listTotalCount: Int? = null,

    @JsonProperty("RESULT")
    val result: AssemblyMemberCurrentHistoryResult? = null
)

data class AssemblyMemberCurrentHistoryResult(
    @JsonProperty("CODE")
    val code: String,

    @JsonProperty("MESSAGE")
    val message: String
)

data class AssemblyMemberCurrentHistoryRowData(
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
    @JsonProperty("UNIT_CD")
    var 대수코드: String? = "",
    @JsonProperty("UNIT_NM")
    var 대수: String? = "",
)