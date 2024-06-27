package jonjal.temp.crawling.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate
import java.time.format.DateTimeFormatter


data class AssemblyTermInfoDto(

    /**
     * ID
     */
    var id: Long? = null,

    @JsonProperty("nokivirranikoinnk")
    var result: List<AssemblyTermInfoHeadAndRow>

)


data class AssemblyTermInfoHeadAndRow(
    @JsonProperty("head")
    var head: List<AssemblyTermInfoHeadItem>? = null,

    @JsonProperty("row")
    var row: List<AssemblyTermInfoRowData>? = null

)

data class AssemblyTermInfoHeadItem(
    @JsonProperty("list_total_count")
    val listTotalCount: Int? = null,

    @JsonProperty("RESULT")
    val result: AssemblyTermInfoResult? = null
)

data class AssemblyTermInfoResult(
    @JsonProperty("CODE")
    val code: String,

    @JsonProperty("MESSAGE")
    val message: String
)

data class AssemblyTermInfoRowData(

    @JsonProperty("ERACO")
    val 대수: String = "",

    @JsonProperty("GCL_ELEC_DIV")
    val 대별선거구분: String? = "",

    @JsonProperty("ELEC_DE")
    private var _선거일: String? = null,

    @JsonProperty("ASBLM_PSNUM")
    private var _의원정수: String? = null,
    @JsonProperty("TERM_BG")
    private var _임기시작: String? = null,
    @JsonProperty("TERM_ED")
    private var _임기종료: String? = null,
    @JsonProperty("PROD")
    val 임기기간: String? = "",
    @JsonProperty("RMK")
    val 비고: String? = ""
) {
    var 의원정수: Int? = null
        get() {
            try {
                return _의원정수?.toInt()
            } catch (e: Exception) {
                return null
            }
        }
    var 선거일: LocalDate? = null
        get() {
            try {
                var result = _선거일?.trim()?.replace(" ", "")
                if (result != null && result!!.last().toString().equals(".")) {
                    result = result!!.substring(0, result!!.length - 1)
                }
                if (result != null && result!!.equals("63.11.26")) {
                    result = "1963.11.26"
                }
                return LocalDate.parse(result, DateTimeFormatter.ofPattern("yyyy.M.d"))
            } catch (e: Exception) {
                return null
            }
        }

    var 임기시작: LocalDate? = null
        get() {
            try {
                var result = _임기시작?.trim()?.replace(" ", "")
                if (result != null && result!!.last().toString().equals(".")) {
                    result = result!!.substring(0, result!!.length - 1)
                }
                return LocalDate.parse(result, DateTimeFormatter.ofPattern("yyyy.M.d"))
            } catch (e: Exception) {
                return null
            }
        }

    var 임기종료: LocalDate? = null
        get() {
            try {
                var result = _임기종료?.trim()?.replace(" ", "")
                if (result != null && result!!.last().toString().equals(".")) {
                    result = result!!.substring(0, result!!.length - 1)
                }
                return LocalDate.parse(result, DateTimeFormatter.ofPattern("yyyy.M.d"))
            } catch (e: Exception) {
                return null
            }
        }
}