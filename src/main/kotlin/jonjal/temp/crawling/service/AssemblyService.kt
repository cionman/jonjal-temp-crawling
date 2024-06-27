package jonjal.temp.crawling.service

import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.Download
import com.microsoft.playwright.Locator
import com.microsoft.playwright.options.LoadState
import com.querydsl.core.NonUniqueResultException
import com.querydsl.core.types.ExpressionUtils
import com.querydsl.jpa.impl.JPAQueryFactory
import jonjal.temp.crawling.dto.*
import jonjal.temp.crawling.entity.*
import jonjal.temp.crawling.entity.QAssemblyCommitteeMember.assemblyCommitteeMember
import jonjal.temp.crawling.entity.QAssemblyMemberBill.assemblyMemberBill
import jonjal.temp.crawling.entity.QAssemblyMemberCurrent.assemblyMemberCurrent
import jonjal.temp.crawling.entity.QAssemblyMemberPast.assemblyMemberPast
import jonjal.temp.crawling.entity.QAssemblyTermInfo.assemblyTermInfo
import jonjal.temp.crawling.repository.*
import org.apache.commons.lang3.StringUtils
import org.apache.pdfbox.Loader
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionTemplate
import org.springframework.web.client.RestTemplate
import java.io.File
import java.net.URI
import java.nio.file.Paths
import java.time.LocalDate
import java.time.format.DateTimeFormatter

const val UPLOAD_PATH = "files/"

@Service
@Transactional(readOnly = true)
class AssemblyService(
    private val assemblyMemberCurrentRepository: AssemblyMemberCurrentRepository,
    private val assemblyMemberPastRepository: AssemblyMemberPastRepository,
    private val assemblyMemberCurrentCommitteeRepository: AssemblyMemberCurrentCommitteeRepository,
    private val assemblyMemberPastCommitteeRepository: AssemblyMemberPastCommitteeRepository,
    private val assemblyMemberCurrentHistoryRepository: AssemblyMemberCurrentHistoryRepository,
    private val assemblyMemberPastHistoryRepository: AssemblyMemberPastHistoryRepository,
    private val assemblyMemberSnsRepository: AssemblyMemberSnsRepository,
    private val assemblyFileDownloadRepository: AssemblyFileDownloadRepository,
    private val assemblyGeneralAttendenceRepository: AssemblyGeneralAttendenceRepository,
    private val assemblyTermInfoRepository: AssemblyTermInfoRepository,
    private val assemblyCommitteeMemberRepository: AssemblyCommitteeMemberRepository,
    private val assemblyCommitteeAttendenceRepository: AssemblyCommitteeAttendenceRepository,
    private val assemblyMemberBillRepository: AssemblyMemberBillRepository,
    private val assemblyMemberGeneralVotingRepository: AssemblyMemberGeneralVotingRepository,
    private val transactionTemplate: TransactionTemplate,
    private val restTemplate: RestTemplate,
    private val browser: BrowserContext,
    private val queryFactory: JPAQueryFactory,
    @Value("\${open.assembly.api.key}")
    private val apiKey: String
) {

    fun getAssemblyMemberCurrentBy국회의원코드(국회의원코드: String?): AssemblyMemberCurrent? {
        return assemblyMemberCurrentRepository.findBy국회의원코드(국회의원코드)
    }

    fun getAssemblyCommitteeMemberBy위원회코드And국회의원코드(위원회코드: String?, 국회의원코드: String?): AssemblyCommitteeMember? {
        return assemblyCommitteeMemberRepository.findBy위원회코드And국회의원코드(위원회코드, 국회의원코드)
    }

    fun getAssemblyMemberCurrentCommitteeBy국회의원코드And경력대수코드And위원회경력(
        의원코드: String?,
        경력대수코드: String?,
        위원회경력: String?
    ): AssemblyMemberCurrentCommittee? {
        return assemblyMemberCurrentCommitteeRepository.findBy국회의원코드And경력대수코드And위원회경력(의원코드, 경력대수코드, 위원회경력)
    }

    fun getAssemblyMemberCurrentHistoryBy국회의원코드And대수코드And의원이력(
        국회의원코드: String?,
        대수코드: String?,
        의원이력: String?
    ): AssemblyMemberCurrentHistory? {
        return assemblyMemberCurrentHistoryRepository.findBy국회의원코드And대수코드And의원이력(국회의원코드, 대수코드, 의원이력)
    }

    fun getAssemblyMemberSnsBy국회의원코드(국회의원코드: String?): AssemblyMemberSns? {
        return assemblyMemberSnsRepository.findBy국회의원코드(국회의원코드)
    }

    fun getAssemblyMemberPastBy국회의원코드And대별코드(국회의원코드: String?, 대별코드: String?): AssemblyMemberPast? {
        return assemblyMemberPastRepository.findBy국회의원코드And대별코드(국회의원코드, 대별코드)
    }

    fun getAssemblyMemberPastCommitteeBy국회의원코드And경력대수코드And위원회경력(
        의원코드: String?,
        경력대수코드: String?,
        위원회경력: String?
    ): AssemblyMemberPastCommittee? {
        return assemblyMemberPastCommitteeRepository.findBy국회의원코드And경력대수코드And위원회경력(의원코드, 경력대수코드, 위원회경력)
    }

    fun getAssemblyMemberPastHistoryBy국회의원코드And경력대수코드And의원이력(
        국회의원코드: String?,
        대수코드: String?,
        의원이력: String?
    ): AssemblyMemberPastHistory? {
        return assemblyMemberPastHistoryRepository.findBy국회의원코드And경력대수코드And의원이력(국회의원코드, 대수코드, 의원이력)
    }

    fun getAssemblyMemberBillBy의안ID(의안ID: String?): AssemblyMemberBill? {
        return assemblyMemberBillRepository.findBy의안ID(의안ID)
    }

    /**
     * 국회의원 인적 사항
     */
    @Transactional
    fun collectAssemblyMemberCurrent() {
        val data: AssemblyMemberCurrentDto = restTemplate.getForEntity(
            URI.create("https://open.assembly.go.kr/portal/openapi/nwvrqwxyaytdsfvhu?KEY=${apiKey}&Type=json&pSize=1000"),  //pSize(페이지당 요청숫자)는 1000이 최대 값이다. 그 상은 에러메세지를 반환한다.
            AssemblyMemberCurrentDto::class.java
        ).body!!
        data.result.forEach {
            if (it.row != null) {
                assemblyMemberCurrentRepository.saveAllAndFlush(
                    it.row!!.map {
                        getAssemblyMemberCurrentBy국회의원코드(it.국회의원코드)?.update(it) ?: AssemblyMemberCurrent.create(it)
                    }
                )
            }
        }
    }


    /**
     * 국회의원 위원회 경력
     */
    @Transactional
    fun collectAssemblyMemberCurrentCommittee() {
        for (pageNum in 1..100) {
            val data = restTemplate.getForEntity(
                URI.create("https://open.assembly.go.kr/portal/openapi/nyzrglyvagmrypezq?KEY=${apiKey}&Type=json&pSize=1000&pIndex=${pageNum}"), // pIndex 페이지 숫자
                AssemblyMemberCurrentCommitteeDto::class.java
            ).body
            data?.result?.forEach {
                if (it.row != null) {
                    assemblyMemberCurrentCommitteeRepository.saveAllAndFlush(
                        it.row!!.map {
                            AssemblyMemberCurrentCommittee.create(it)
                        }
                    )
                }
            }
            if (data?.result?.get(1)?.row!!.size < 1000) break
        }
    }

    /**
     * 국회의원 의원이력
     */
    @Transactional
    fun collectAssemblyMemberCurrentHistory() {
        val data: AssemblyMemberCurrentHistoryDto =
            restTemplate.getForEntity(
                URI.create("https://open.assembly.go.kr/portal/openapi/nexgtxtmaamffofof?KEY=${apiKey}&Type=json&pSize=1000"),
                AssemblyMemberCurrentHistoryDto::class.java
            ).body!!
        data.result.forEach {
            if (it.row != null) {
                assemblyMemberCurrentHistoryRepository.saveAllAndFlush(
                    it.row!!.map {
                        getAssemblyMemberCurrentHistoryBy국회의원코드And대수코드And의원이력(it.국회의원코드, it.대수코드, it.의원이력)?.update(it)
                            ?: AssemblyMemberCurrentHistory.create(it)
                    }
                )
            }
        }
    }

    /**
     * 국회의원 SNS 정보
     */
    @Transactional
    fun collectAssemblyMemberSns() {
        val data: AssemblyMemberSnsDto = restTemplate.getForEntity(
            URI.create("https://open.assembly.go.kr/portal/openapi/negnlnyvatsjwocar?KEY=${apiKey}&Type=json&pSize=1000"),
            AssemblyMemberSnsDto::class.java
        ).body!!
        data.result.forEach {
            if (it.row != null) {
                assemblyMemberSnsRepository.saveAllAndFlush(
                    it.row!!.map {
                        getAssemblyMemberSnsBy국회의원코드(it.국회의원코드)?.update(it) ?: AssemblyMemberSns.create(it)
                    }
                )
            }
        }
    }


    /**
     * 역대 국회의원 인적사항
     */
    @Transactional
    fun collectAssemblyMemberPast() {
        // 대별 코드값
        listOf<String>(
            "100001", "100002", "100003", "100004", "100005", "100006", "100007", "100008", "100009", "100010",
            "100011", "100012", "100013", "100014", "100015", "100016", "100017", "100018", "100019", "100020",
            "100021", "100022", "100023"
        )
            .forEach {
                kotlin.runCatching {
                    restTemplate.getForEntity(
                        URI.create("https://open.assembly.go.kr/portal/openapi/npffdutiapkzbfyvr?KEY=${apiKey}&Type=json&pSize=1000&UNIT_CD=${it}"),
                        AssemblyMemberPastDto::class.java
                    ).body!!.result.forEach {
                        if (it.row != null) {
                            assemblyMemberPastRepository.saveAllAndFlush(
                                it.row!!.map {
                                    getAssemblyMemberPastBy국회의원코드And대별코드(it.국회의원코드, it.대별코드)?.update(it)
                                        ?: AssemblyMemberPast.create(it)
                                }
                            )
                        }
                    }
                }
            }

    }

    /***
     * 역대 국회의원 위원회 경력
     */
    @Transactional
    fun collectAssemblyMemberPastCommittee() {
        // 대별 코드값
        listOf<String>(
            "100001", "100002", "100003", "100004", "100005", "100006", "100007", "100008", "100009", "100010",
            "100011", "100012", "100013", "100014", "100015", "100016", "100017", "100018", "100019", "100020",
            "100021", "100022", "100023"
        )
            .forEach {
                kotlin.runCatching {
                    for (pageNum in 1..100) {
                        val data = restTemplate.getForEntity(
                            URI.create("https://open.assembly.go.kr/portal/openapi/nqbeopthavwwfbekw?KEY=${apiKey}&Type=json&pSize=1000&PROFILE_UNIT_CD=${it}&pIndex=${pageNum}"), // PROFILE_UNIT_CD 대별코드
                            AssemblyMemberPastCommitteeDto::class.java
                        ).body
                        data?.result?.forEach {
                            if (it.row != null) {
                                assemblyMemberPastCommitteeRepository.saveAllAndFlush(
                                    it.row!!.map {
                                        getAssemblyMemberPastCommitteeBy국회의원코드And경력대수코드And위원회경력(
                                            it.국회의원코드,
                                            it.경력대수코드,
                                            it.위원회경력
                                        )?.update(it)
                                            ?: AssemblyMemberPastCommittee.create(it)
                                    }
                                )
                            }
                        }
                        if (data?.result?.get(1)?.row!!.size < 1000) break
                    }
                }
            }

    }

    /**
     * 역대 국회의원 의원이력
     */
    @Transactional
    fun collectAssemblyMemberPastHistory() {
        listOf<String>(
            "100001", "100002", "100003", "100004", "100005", "100006", "100007", "100008", "100009", "100010",
            "100011", "100012", "100013", "100014", "100015", "100016", "100017", "100018", "100019", "100020",
            "100021", "100022", "100023"
        )
            .forEach {
                kotlin.runCatching {
                    restTemplate.getForEntity(
                        URI.create("https://open.assembly.go.kr/portal/openapi/nfzegpkvaclgtscxt?KEY=${apiKey}&Type=json&pSize=1000&PROFILE_UNIT_CD=${it}"),
                        AssemblyMemberPastHistoryDto::class.java
                    ).body!!.result.forEach {
                        if (it.row != null) {
                            assemblyMemberPastHistoryRepository.saveAllAndFlush(
                                it.row!!.map {
                                    getAssemblyMemberPastHistoryBy국회의원코드And경력대수코드And의원이력(
                                        it.국회의원코드,
                                        it.경력대수코드,
                                        it.의원이력
                                    )?.update(it)
                                        ?: AssemblyMemberPastHistory.create(it)
                                }
                            )
                        }
                    }
                }
            }

    }

    /**
     * 역대 국회 선거일, 의원정수, 임기정보
     *
     */
    @Transactional
    fun collectAssemblyTermInfo() {
        val data: AssemblyTermInfoDto = restTemplate.getForEntity(
            URI.create("https://open.assembly.go.kr/portal/openapi/nokivirranikoinnk?KEY=${apiKey}&Type=json&pSize=1000"),
            AssemblyTermInfoDto::class.java
        ).body!!
        data.result.forEach {
            if (it.row != null) {
                assemblyTermInfoRepository.saveAllAndFlush(
                    it.row!!.map {
                        AssemblyTermInfo.create(it)
                    }
                )
            }
        }
    }


    /**
     * 위원회 위원 명단
     */
    @Transactional
    fun collectAssemblyCommitteeMember() {
        val data: AssemblyCommitteeMemberDto =
            restTemplate.getForEntity(
                URI.create("https://open.assembly.go.kr/portal/openapi/nktulghcadyhmiqxi?KEY=${apiKey}&Type=json&pSize=1000"),
                AssemblyCommitteeMemberDto::class.java
            ).body!!
        data.result.forEach {
            if (it.row != null) {
                assemblyCommitteeMemberRepository.saveAllAndFlush(
                    it.row!!.map {
                        getAssemblyCommitteeMemberBy위원회코드And국회의원코드(it.위원회코드, it.국회의원코드)?.update(it)
                            ?: AssemblyCommitteeMember.create(it)
                    }
                )
            }
        }
    }


    /**
     * 국회의원 발의법률안
     */
    @Transactional
    fun collectAssemblyMemberBill() {
        (1..21).map { it.toString().padStart(2, '0') }
            .forEach {
                try {
                    for (pageNum in 1..100) {
                        val data = restTemplate.getForEntity(
                            URI.create("https://open.assembly.go.kr/portal/openapi/nzmimeepazxkubdpn?KEY=${apiKey}&Type=json&pIndex=${pageNum}&pSize=1000&AGE=${it}"),
                            AssemblyMemberBillDto::class.java
                        ).body
                        data?.result?.forEach {
                            if (it.row != null) {
                                assemblyMemberBillRepository.saveAllAndFlush(
                                    it.row!!.map {
                                        getAssemblyMemberBillBy의안ID(
                                            it.의안ID,
                                        )?.update(it)
                                            ?: AssemblyMemberBill.create(it)
                                    }
                                )
                            }
                        }
                        if (data?.result?.get(1)?.row!!.size < 1000) break
                    }
                } catch (e: Exception) {
                    return@forEach
                }

            }

    }

    /**
     * 국회의원 본회의 표결정보
     */
    fun collectAssemblyMemberGeneralVoting() {
        (20..21).map { it.toString().padStart(2, '0') }
            .forEach {
                val billIdList: List<String> = getBillIdListByAge(it)
                for (billId in billIdList) {

                    try {
                        val data =
                            restTemplate.getForEntity(
                                URI.create("https://open.assembly.go.kr/portal/openapi/nojepdqqaweusdfbi?KEY=${apiKey}&Type=json&pSize=1000&AGE=${it}&BILL_ID=${billId}"),
                                AssemblyMemberGeneralVotingDto::class.java
                            ).body
                        data?.result?.forEach {
                            if (it.row != null) {
                                val rowdata = it.row
                                transactionTemplate.execute {
                                    assemblyMemberGeneralVotingRepository.saveAllAndFlush(
                                        rowdata!!.map {
                                            AssemblyMemberGeneralVoting.create(it)
                                        }
                                    )
                                }

                            }
                        }
                    } catch (e: Exception) {
                        continue
                    }
                }

            }

    }

    private fun getBillIdListByAge(age: String): List<String> {
        return assemblyMemberBillRepository.from()
            .select(assemblyMemberBill.의안ID)
            .where(
                ExpressionUtils.allOf(
                    assemblyMemberBill.대수.eq(age),
                    //assemblyMemberBill.본회의심의결과.isNotNull(),
                )
            )
            .fetch()
    }

    /**
     * 본회의 출석
     *
     * PDF 파싱은 한줄씩 읽어드려 판단하는 방식으로 되어 있다.
     */
    @Transactional
    fun collectGeneralAttendenceStatus() {
        val page = browser.newPage()
        page.navigate("https://open.assembly.go.kr/portal/data/service/selectServicePage.do?infId=O4Q5B50011905O18367&infSeq=1&isInfsPop=Y")
        page.waitForLoadState(LoadState.DOMCONTENTLOADED)
        page.waitForLoadState(LoadState.NETWORKIDLE)

        val downloadListLocator: List<Locator> = page.locator("#file-data-list > li > span.hdNew_viewFileNm").all()
        for (locator in downloadListLocator) {
            val locatorTxt = locator.textContent()
            val downloadFilename = locatorTxt.substring(0, locatorTxt.lastIndexOf(" ("))
            if (assemblyFileDownloadRepository.existsById(downloadFilename)) continue
            val download: Download = page.waitForDownload {
                locator.click()
            }
            download.saveAs(Paths.get("${UPLOAD_PATH}", downloadFilename))
            assemblyFileDownloadRepository.saveAndFlush(AssemblyFileDownload.create(downloadFilename))
            val document: PDDocument = Loader.loadPDF(File("${UPLOAD_PATH}${downloadFilename}"))
            val stripper = PDFTextStripper()
            val docString = stripper.getText(document)

            /**
             * PDF 파싱은 한줄씩 읽어드려 판단하는 방식으로 되어 있다.
             * 그래서 불필요한 데이터가 있는 줄들을 걸러내기위한 단어 리스트 이다.
             */
            val ignoreList = listOf(
                "*",
                "                 ",
                "1차",
                "구분",
                "의원명",
                "(",
                "회의",
                "일수",
                "결석",
                "신고서",
                "출장",
                "당일",
                "에는",
                "기재한",
                "한 결석신고서"
            )
            val arrDocString = docString.split("\n")
            val arrDay = arrDocString[1].trim().split(" ")
            val dayCnt = arrDay.size
            // 몇대 국회인지 체크하기위한 변수
            val checkDay =
                LocalDate.parse(
                    arrDay[0].replace("(", "")
                        .replace(")", ""), DateTimeFormatter.ofPattern("yyyy년MM월dd일")
                )

            val termInfo = getTermInfo(checkDay)
            val 대수 = termInfo.대수
            arrDocString.forEachIndexed { index, line ->
                val isIgnoreStart = ignoreList.any { line.trim().startsWith(it) }
                if (isIgnoreStart.not() && StringUtils.isNotEmpty(line.trim())) {
                    val arrLine = line.trim().split(" ")
                    if (arrLine.size < 2) return@forEachIndexed
                    var 이름 = arrLine[0]
                    var 정당명 = arrLine[1]
                    var 국회의원코드 = ""

                    /**
                     *  아래 정당명 변경의 경우 주기적으로 정보를 업데이트하고 파일을 빠른시일내에 파싱하는 경우 문제가 감소할 것으로 생각된다.
                     *
                     */
                    //동명 이인이 한자로 처리 되어 있다.
                    if ("제21대".equals(대수) && "金炳旭".equals(이름)) {
                        이름 = "김병욱"
                    }
                    // 동명이인
                    if ("제21대".equals(대수) && "이수진(비)".equals(이름)) {
                        이름 = "이수진"
                    }
                    //정당명 변경
                    if ("제21대".equals(대수) && listOf(
                            "김남국",
                            "김홍걸",
                            "민형배",
                            "박병석",
                            "강민정",
                            "김의겸",
                            "최강욱",
                            "양이원영"
                        ).contains(이름)
                    ) {
                        정당명 = "더불어민주당"
                    }
                    //정당명 변경
                    if ("제21대".equals(대수) && listOf("설훈", "오영환", "김종민", "박영순", "홍영표").contains(이름)) {
                        정당명 = "새로운미래"
                    }
                    // 정당명 변경
                    if ("제21대".equals(대수) && listOf("용혜인").contains(이름)) {
                        정당명 = "기본소득당"
                    }
                    //정당명 변경

                    if ("제21대".equals(대수) && "황보승희".equals(이름)) {
                        정당명 = "자유통일당"
                    }

                    //정당명 변경
                    if ("제21대".equals(대수) && "황운하".equals(이름)) {
                        정당명 = "조국혁신당"
                    }
                    //정당명 변경
                    if ("제21대".equals(대수) && listOf("양정숙", "양향자", "이원욱", "조응천").contains(이름)) {
                        정당명 = "개혁신당"
                    }

                    //정당명 변경
                    if ("제21대".equals(대수) && listOf(
                            "김영주",
                            "이상민",
                            "조정훈",
                            "임병헌",
                            "권은희",
                            "이태규",
                            "최연숙",
                            "박덕흠",
                            "이용호",
                            "전봉민",
                            "송언석",
                            "윤상현",
                            "홍준표",
                            "김태호",
                            "강기윤",
                            "강대식",
                            "강민국",
                            "구자근",
                            "권명호",
                            "권성동",
                            "권영세",
                            "김기현",
                            "김도읍",
                            "김미애",
                            "김상훈",
                            "김석기",
                            "김성원",
                            "김승수",
                            "김영식",
                            "김예지",
                            "김용판",
                            "김웅",
                            "김정재",
                            "김태흠",
                            "김형동",
                            "김희곤",
                            "김희국",
                            "류성걸",
                            "박대수",
                            "박대출",
                            "박덕흠",
                            "박성민",
                            "박성중",
                            "박수영",
                            "박완수",
                            "박진",
                            "박형수",
                            "배현진",
                            "백종헌",
                            "서범수",
                            "서병수"
                        ).contains(이름)
                    ) {
                        정당명 = "국민의힘"
                    }
                    if ("제21대".equals(대수) && listOf(
                            "이상헌",
                            "전혜숙",
                            "윤관석",
                            "이성만",
                            "하영제",
                            "김진표",
                            "박완주",
                            "곽상도",
                            "윤미향",
                            "이상직"
                        ).contains(이름)
                    ) {
                        정당명 = "무소속"
                    }
                    if ("제21대".equals(대수) && "미래통합당".equals(정당명)) {
                        정당명 = "국민의힘"
                    }
                    if ("제21대".equals(대수) && listOf("김선교", "김은혜").contains(이름)) {
                        정당명 = "미래통합당"
                    }
                    if ("제21대".equals(대수) && "녹색정의당".equals(정당명)) {
                        정당명 = "정의당"
                    }
                    if ("제21대".equals(대수) && "김병욱".equals(이름) && "무소속".equals(정당명)) {
                        정당명 = "국민의힘"
                    }

                    if ("제21대".equals(대수) && "김병욱".equals(이름) && "미래통합당".equals(정당명)) {
                        정당명 = "국민의힘"
                    }


                    val currentMember = queryFactory.selectFrom(assemblyMemberCurrent)
                        .where(
                            ExpressionUtils.allOf(
                                assemblyMemberCurrent.이름.eq(이름),
                                assemblyMemberCurrent.정당명.eq(정당명),
                            )
                        ).fetchOne()
                    if (currentMember == null) {
                        if ("제20대".equals(대수) && listOf("최경환(평)", "최경환(한)").contains(이름)) {
                            이름 = "최경환"
                        }
                        val pastMember = try {
                            queryFactory.selectFrom(assemblyMemberPast)
                                .where(
                                    ExpressionUtils.allOf(
                                        if (!"金成泰".equals(이름)) assemblyMemberPast.이름.eq(이름) else null,
                                        if ("金成泰".equals(이름)) assemblyMemberPast.한자명.eq(이름) else if ("김성태".equals(이름)) assemblyMemberPast.한자명.ne(
                                            "金成泰"
                                        ) else null,
                                        assemblyMemberPast.대.eq(termInfo.대수)
                                    )
                                ).fetchOne()
                        } catch (e: NonUniqueResultException) {
                            if ("최경환".equals(이름)) {
                                return@forEachIndexed
                            }
                            queryFactory.selectFrom(assemblyMemberPast)
                                .where(
                                    ExpressionUtils.allOf(
                                        if (!"金成泰".equals(이름)) assemblyMemberPast.이름.eq(이름) else null,
                                        if ("金成泰".equals(이름)) assemblyMemberPast.한자명.eq(이름) else if ("김성태".equals(이름)) assemblyMemberPast.한자명.ne(
                                            "金成泰"
                                        ) else null,
                                        assemblyMemberPast.정당명.eq(정당명),
                                        assemblyMemberPast.대.eq(termInfo.대수)
                                    )
                                ).fetchOne()
                        }
                        국회의원코드 = pastMember!!.국회의원코드!!
                    } else {
                        국회의원코드 = currentMember.국회의원코드!!
                    }
                    assemblyGeneralAttendenceRepository.saveAndFlush(
                        AssemblyGeneralAttendence.create(
                            AssemblyGeneralAttendenceDto(
                                대수 = termInfo.대수,
                                이름 = 이름,
                                정당명 = 정당명,
                                회기 = downloadFilename.substring(0, downloadFilename.indexOf("(")),
                                회기타입 = downloadFilename.substring(
                                    downloadFilename.indexOf("("),
                                    downloadFilename.indexOf(")") + 1
                                ),
                                회의일수 = arrLine[2 + dayCnt].toInt(),
                                출석 = arrLine[3 + dayCnt].toInt(),
                                결석 = arrLine[4 + dayCnt].toInt(),
                                청가 = arrLine[5 + dayCnt].toInt(),
                                출장 = arrLine[6 + dayCnt].toInt(),
                                결석신고서 = arrLine[7 + dayCnt].toInt(),
                                국회의원코드 = 국회의원코드
                            )
                        )
                    )
                    println("이름: ${arrLine[0]}, 정당: ${arrLine[1]}, 회의일수: ${arrLine[2 + dayCnt]}, 출석: ${arrLine[3 + dayCnt]}, 결석: ${arrLine[4 + dayCnt]}, 청가: ${arrLine[5 + dayCnt]}, 출장: ${arrLine[6 + dayCnt]}, 결석신고서: ${arrLine[7 + dayCnt]}")
                }

            }
        }
    }

    private fun getTermInfo(checkDay: LocalDate?): AssemblyTermInfo =
        queryFactory.selectFrom(assemblyTermInfo)
            .where(assemblyTermInfo.임기시작.loe(checkDay).and(assemblyTermInfo.임기종료.goe(checkDay)))
            .fetchFirst()

    //////

    /**
     * 위원회 출결
     */
    @Transactional
    fun collectCommitteeAttendenceStatus() {
        var page = browser.newPage()
        page.navigate("https://open.assembly.go.kr/portal/data/service/selectServicePage.do?infId=OND4F9001191DA18437&infSeq=1&isInfsPop=Y")
        page.waitForLoadState(LoadState.DOMCONTENTLOADED)
        page.waitForLoadState(LoadState.NETWORKIDLE)

        val downloadListLocator: List<Locator> = page.locator("#file-data-list > li > span.hdNew_viewFileNm").all()
        for (locator in downloadListLocator) {
            val locatorTxt = locator.textContent()
            val downloadFilename = locatorTxt.substring(0, locatorTxt.lastIndexOf(" ("))
            if (downloadFilename.equals("2023년도 10월 중 특위 출결현황.pdf")) continue
            if (downloadFilename.equals("2022년 11월 상임위 출결현황.pdf")) continue
            if (downloadFilename.equals("2022년 7월 상임위 출결현황.pdf")) continue
            if (downloadFilename.equals("2022년 2월 특위 출결현황.pdf")) continue
            val fileExt = downloadFilename.substringAfterLast('.', "")
            val fileYear = downloadFilename.substring(0, 4).toInt()
            if (fileExt.equals("pdf", true).not() || fileYear < 2022) return
            if (assemblyFileDownloadRepository.existsById(downloadFilename)) continue
            val download: Download = page.waitForDownload {
                locator.click()
            }
            download.saveAs(Paths.get("${UPLOAD_PATH}", downloadFilename))
            assemblyFileDownloadRepository.saveAndFlush(AssemblyFileDownload.create(downloadFilename))
            val document: PDDocument = Loader.loadPDF(File("${UPLOAD_PATH}${downloadFilename}"))
            val stripper = PDFTextStripper()

            for (page in 1..document.numberOfPages) {
                stripper.startPage = page
                stripper.endPage = page
                val pageText = stripper.getText(document)
                println("Page $page:\n$pageText")
                val firstChar = pageText[0].toString()
                val arrPageString = pageText.split("\n")
                val dayCnt =
                    if (firstChar.equals("제")) arrPageString[1].trim().split(" ").size else arrPageString[0].trim()
                        .split(" ").size
                val arrDays = if (firstChar.equals("제")) arrPageString[2].trim().split(" ") else arrPageString[1].trim()
                    .split(" ")
                val firstMonth = arrDays[0].substring(0, 2).toInt()
                val firstDay = arrDays[1].substring(0, 2).toInt()
                val checkDay = LocalDate.of(fileYear, firstMonth, firstDay)
                val termInfo = getTermInfo(checkDay)
                val committeeName = arrPageString.first { line ->
                    line.endsWith("위원회")
                }

                arrPageString.forEachIndexed { index, line ->
                    println("line :::: ${line}")
                    var 회기 = ""
                    if (line.trim().isEmpty()) return@forEachIndexed
                    if (line.isNotEmpty() && line.endsWith("위원회")) {
                        return@forEachIndexed
                    }
                    if (line.isNotEmpty() && line.first().equals('제') && (line.last().equals('회') || line.last()
                            .equals(')'))
                    ) {
                        회기 = line
                        return@forEachIndexed
                    }
                    if (line.isNotEmpty() && line.first().equals('(') || line.first().equals('`') || line.trim()
                            .equals("의원명") || line.trim().equals("출장") || line.last()
                            .equals('차') || line.last().equals('일') || line.last().equals('서')
                    ) {
                        return@forEachIndexed
                    }


                    val arrLine = line.trim().split(" ")
                    println("arrLine ::: ${arrLine}")
                    println("arrLine0 ::: ${arrLine[0]}")
                    val firstNumberIndex = findFirstNumberIndex(arrLine)
                    val 이름 = arrLine[0].substring(0, arrLine[0].indexOf("("))
                    val 한자명 = arrLine[0].substring(arrLine[0].indexOf("(") + 1, arrLine[0].indexOf(")"))
                    var 국회의원코드 = ""
                    val committeeMember = queryFactory.selectFrom(assemblyCommitteeMember)
                        .where(
                            ExpressionUtils.allOf(
                                assemblyCommitteeMember.위원회명.eq(committeeName),
                                assemblyCommitteeMember.이름.eq(이름),

                                //민형배 pdf에는 한자가 있으나 api데이터에는 한자가 없다.
                                //박성민 pdf의 한자와 api 한자가 다르다.
                                if (!listOf("민형배", "박성민").contains(이름)) assemblyCommitteeMember.한자명.eq(한자명) else null,
                            )
                        ).fetchOne()
                    if (committeeMember != null) {
                        국회의원코드 = committeeMember.국회의원코드!!
                    } else {
                        val currentMember = try {
                            queryFactory.selectFrom(assemblyMemberCurrent)
                                .where(
                                    ExpressionUtils.allOf(
                                        assemblyMemberCurrent.이름.eq(이름),
                                        if (!listOf(
                                                "민형배",
                                                "박성민",
                                                "박정하"
                                            ).contains(이름)
                                        ) assemblyMemberCurrent.한자명.eq(한자명) else null,
                                    )
                                ).fetchOne()
                        } catch (_: Exception) {
                            return@forEachIndexed
                        }
                        if (currentMember != null) {
                            국회의원코드 = currentMember.국회의원코드!!
                        } else {
                            val pastMember = try {
                                queryFactory.selectFrom(assemblyMemberPast)
                                    .where(
                                        ExpressionUtils.allOf(
                                            assemblyMemberPast.이름.eq(이름),
                                            assemblyMemberPast.한자명.eq(한자명),
                                            assemblyMemberPast.대.eq(termInfo.대수)
                                        )
                                    ).fetchOne()
                            } catch (_: Exception) {
                                return@forEachIndexed
                            }
                            국회의원코드 = pastMember?.국회의원코드!!
                        }
                    }






                    assemblyCommitteeAttendenceRepository.saveAndFlush(
                        AssemblyCommitteeAttendence.create(
                            AssemblyCommitteeAttendenceDto(
                                대수 = termInfo.대수,
                                이름 = 이름,
                                파일명 = downloadFilename,
                                위원회명 = committeeName,
                                회기 = 회기,
                                회의일수 = arrLine[firstNumberIndex].toInt(),
                                출석 = arrLine[firstNumberIndex + 1].toInt(),
                                결석 = arrLine[firstNumberIndex + 2].toInt(),
                                청가 = arrLine[firstNumberIndex + 3].toInt(),
                                출장 = arrLine[firstNumberIndex + 4].toInt(),
                                결석신고서 = arrLine[firstNumberIndex + 5].toInt(),
                                국회의원코드 = 국회의원코드
                            )
                        )
                    )
                }

            }
        }
    }

    private fun findFirstNumberIndex(arrString: List<String>): Int {
        arrString.forEachIndexed { index, str ->
            try {
                str.toInt()
            } catch (e: NumberFormatException) {
                return@forEachIndexed
            }
            return index
        }
        throw NumberFormatException()
    }
}