package jonjal.temp.crawling.repository

import jonjal.temp.common.repository.BaseRepository
import jonjal.temp.crawling.entity.AssemblyMemberPast

interface AssemblyMemberPastRepository : BaseRepository<AssemblyMemberPast, Long> {
    fun findBy국회의원코드And대별코드(국회의원코드: String?, 대별코드: String?): AssemblyMemberPast?
}