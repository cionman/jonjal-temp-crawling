package jonjal.temp.crawling

import jonjal.temp.crawling.service.AssemblyService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JonjalTempCrawlingApplicationTests (
    @Autowired
    var assemblyService: AssemblyService,
){


    @Test
    fun contextLoads() {
        assemblyService.collectAssemblyTermInfo()
        assemblyService.collectAssemblyMemberCurrent()
        assemblyService.collectAssemblyMemberPast()
        assemblyService.collectAssemblyCommitteeMember()
        assemblyService.collectAssemblyMemberCurrentCommittee()
        assemblyService.collectAssemblyMemberCurrentHistory()
        assemblyService.collectAssemblyMemberSns()
        assemblyService.collectAssemblyMemberPastCommittee()
        assemblyService.collectAssemblyMemberPastHistory()
        assemblyService.collectAssemblyMemberBill()
        assemblyService.collectCommitteeAttendenceStatus()
        assemblyService.collectGeneralAttendenceStatus()
        assemblyService.collectAssemblyMemberGeneralVoting()
    }

}
