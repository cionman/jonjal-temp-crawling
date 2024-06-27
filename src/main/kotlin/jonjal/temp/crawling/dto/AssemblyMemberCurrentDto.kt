package jonjal.temp.crawling.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class AssemblyMemberCurrentDto(

    /**
     * ID
     */
    var id: Long? = null,

    @JsonProperty("nwvrqwxyaytdsfvhu")
    var result: List<AssemblyMemberCurrentHeadAndRow>

)


data class AssemblyMemberCurrentHeadAndRow(
    @JsonProperty("head")
    var head: List<AssemblyMemberCurrentHeadItem>? = null,

    @JsonProperty("row")
    var row: List<AssemblyMemberCurrentRowData>? = null

)

data class AssemblyMemberCurrentHeadItem(
    @JsonProperty("list_total_count")
    val listTotalCount: Int? = null,

    @JsonProperty("RESULT")
    val result: AssemblyMemberCurrentResult? = null
)

data class AssemblyMemberCurrentResult(
    @JsonProperty("CODE")
    val code: String,

    @JsonProperty("MESSAGE")
    val message: String
)

data class AssemblyMemberCurrentRowData(
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
    var 생년월일: LocalDate?,


    /**
     * 직책명
     */
    @JsonProperty("JOB_RES_NM")
    var 직책명: String? = "",

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

    @JsonProperty("CMIT_NM")
    var 대표위원회: String? = "",

    @JsonProperty("CMITS")
    var 소속위원회목록: String? = "",

    @JsonProperty("REELE_GBN_NM")
    var 재선: String? = "",

    @JsonProperty("UNITS")
    var 당선: String? = "",

    @JsonProperty("SEX_GBN_NM")
    var 성별: String? = "",

    @JsonProperty("TEL_NO")
    var 전화번호: String? = "",

    @JsonProperty("E_MAIL")
    var 이메일: String? = "",

    @JsonProperty("HOMEPAGE")
    var 홈페이지: String? = "",

    @JsonProperty("STAFF")
    var 보좌관: String? = "",

    @JsonProperty("SECRETARY")
    var 비서관: String? = "",

    @JsonProperty("SECRETARY2")
    var 비서: String? = "",

    @JsonProperty("MONA_CD")
    var 국회의원코드: String? = "",

    @JsonProperty("MEM_TITLE")
    var 약력: String? = "",

    @JsonProperty("ASSEM_ADDR")
    var 사무실호실: String? = "",
)