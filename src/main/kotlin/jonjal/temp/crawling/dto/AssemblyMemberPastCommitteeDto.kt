package jonjal.temp.crawling.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AssemblyMemberPastCommitteeDto(

    /**
     * ID
     */
    var id: Long? = null,

    @JsonProperty("nqbeopthavwwfbekw")
    var result: List<AssemblyMemberPastCommitteeHeadAndRow>

)


data class AssemblyMemberPastCommitteeHeadAndRow(
    @JsonProperty("head")
    var head: List<AssemblyMemberPastCommitteeHeadItem>? = null,

    @JsonProperty("row")
    var row: List<AssemblyMemberPastCommitteeRowData>? = null

)

data class AssemblyMemberPastCommitteeHeadItem(
    @JsonProperty("list_total_count")
    val listTotalCount: Int? = null,

    @JsonProperty("RESULT")
    val result: AssemblyMemberPastCommitteeResult? = null
)

data class AssemblyMemberPastCommitteeResult(
    @JsonProperty("CODE")
    val code: String,

    @JsonProperty("MESSAGE")
    val message: String
)

data class AssemblyMemberPastCommitteeRowData(
    @JsonProperty("PROFILE_CD")
    var 구분코드: String? = "",
    @JsonProperty("PROFILE_NM")
    var 구분: String? = "",
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