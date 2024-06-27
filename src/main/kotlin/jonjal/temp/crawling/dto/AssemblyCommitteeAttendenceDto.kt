package jonjal.temp.crawling.dto

data class AssemblyCommitteeAttendenceDto(
    val 대수: String = "",
    val 이름: String? = "",
    val 파일명: String? = "",
    val 위원회명: String? = "",
    val 회기: String? = "",
    val 회기타입: String? = "",
    val 회의일수: Int? = null,
    val 출석: Int? = null,
    val 결석: Int? = null,
    val 청가: Int? = null,
    val 출장: Int? = null,
    val 결석신고서: Int? = null,
    val 국회의원코드: String? = null,

)