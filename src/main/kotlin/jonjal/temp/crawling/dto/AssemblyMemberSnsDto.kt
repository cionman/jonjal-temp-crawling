package jonjal.temp.crawling.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AssemblyMemberSnsDto(

    /**
     * ID
     */
    var id: Long? = null,

    @JsonProperty("negnlnyvatsjwocar")
    var result: List<AssemblyMemberSnsHeadAndRow>

)


data class AssemblyMemberSnsHeadAndRow(
    @JsonProperty("head")
    var head: List<AssemblyMemberSnsHeadItem>? = null,

    @JsonProperty("row")
    var row: List<AssemblyMemberSnsRowData>? = null

)

data class AssemblyMemberSnsHeadItem(
    @JsonProperty("list_total_count")
    val listTotalCount: Int? = null,

    @JsonProperty("RESULT")
    val result: AssemblyMemberSnsResult? = null
)

data class AssemblyMemberSnsResult(
    @JsonProperty("CODE")
    val code: String,

    @JsonProperty("MESSAGE")
    val message: String
)

data class AssemblyMemberSnsRowData(
    @JsonProperty("HG_NM")
    var 이름: String? = "",
    @JsonProperty("T_URL")
    var 트위터: String? = "",
    @JsonProperty("F_URL")
    var 페이스북: String? = "",
    @JsonProperty("Y_URL")
    var 유튜브: String? = "",
    @JsonProperty("B_URL")
    var 블로그: String? = "",
    @JsonProperty("MONA_CD")
    var 국회의원코드: String? = "",
)