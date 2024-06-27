package jonjal.temp.crawling.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class AssemblyMemberPastDto(

    /**
     * ID
     */
    var id: Long? = null,

    @JsonProperty("npffdutiapkzbfyvr")
    var result: List<AssemblyMemberPastHeadAndRow>

)


data class AssemblyMemberPastHeadAndRow(
    @JsonProperty("head")
    var head: List<AssemblyMemberPastHeadItem>? = null,

    @JsonProperty("row")
    var row: List<AssemblyMemberPastRowData>? = null

)

data class AssemblyMemberPastHeadItem(
    @JsonProperty("list_total_count")
    val listTotalCount: Int? = null,

    @JsonProperty("RESULT")
    val result: AssemblyMemberPastResult? = null
)

data class AssemblyMemberPastResult(
    @JsonProperty("CODE")
    val code: String,

    @JsonProperty("MESSAGE")
    val message: String
)

data class AssemblyMemberPastRowData(
    /**
     * 이름
     */
    @JsonProperty("HG_NM")
    var 이름: String? = "",


    /**
     * 한자명
     */
    @JsonProperty("HJ_NM")
    var 한자명: String? = "",

    /**
     *
     */
    @JsonProperty("ENG_NM")
    var 영문명칭: String? = "",

    /**
     * 음양력
     */
    @JsonProperty("BTH_GBN_NM")
    var 음양력: String? = "",

    /**
     * 생년월일
     */
    @JsonProperty("BTH_DATE")
    private var _생년월일: String?,


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

    @JsonProperty("ELECT_GBN_NM")
    var 선거구구분: String? = "",


    @JsonProperty("REELE_GBN_NM")
    var 재선: String? = "",

    @JsonProperty("UNITS")
    var 당선: String? = "",

    @JsonProperty("SEX_GBN_NM")
    var 성별: String? = "",


    @JsonProperty("MONA_CD")
    var 국회의원코드: String? = "",

    @JsonProperty("UNIT_CD")
    var 대별코드: String? = "",
    @JsonProperty("UNIT_NM")
    var 대: String? = "",
) {
    var 생년월일: LocalDate? = null
        get() {
            try {
                return LocalDate.parse(_생년월일, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            } catch (e: Exception) {
                return null
            }
        }
}