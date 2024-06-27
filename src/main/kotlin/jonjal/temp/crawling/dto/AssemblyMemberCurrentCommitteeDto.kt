package jonjal.temp.crawling.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AssemblyMemberCurrentCommitteeDto(

    /**
     * ID
     */
    var id: Long? = null,

    @JsonProperty("nyzrglyvagmrypezq")
    var result: List<AssemblyMemberCurrentCommitteeHeadAndRow>

)


data class AssemblyMemberCurrentCommitteeHeadAndRow(
    @JsonProperty("head")
    var head: List<AssemblyMemberCurrentCommitteeHeadItem>? = null,

    @JsonProperty("row")
    var row: List<AssemblyMemberCurrentCommitteeRowData>? = null

)

data class AssemblyMemberCurrentCommitteeHeadItem(
    @JsonProperty("list_total_count")
    val listTotalCount: Int? = null,

    @JsonProperty("RESULT")
    val result: AssemblyMemberCurrentCommitteeResult? = null
)

data class AssemblyMemberCurrentCommitteeResult(
    @JsonProperty("CODE")
    val code: String,

    @JsonProperty("MESSAGE")
    val message: String
)

data class AssemblyMemberCurrentCommitteeRowData(
    @JsonProperty("HG_NM")
    var 의원이름한글: String? = "",
    @JsonProperty("HJ_NM")
    var 의원이름한자: String? = "",
    @JsonProperty("FRTO_DATE")
    var 활동기간: String? = "",
    @JsonProperty("PROFILE_SJ")
    var 위원회경력: String? = "",
    @JsonProperty("MONA_CD")
    var 국회의원코드: String? = "",
    @JsonProperty("PROFILE_UNIT_CD")
    var 경력대수코드: String? = "",
    @JsonProperty("PROFILE_UNIT_NM")
    var 경력대수: String? = "",
)