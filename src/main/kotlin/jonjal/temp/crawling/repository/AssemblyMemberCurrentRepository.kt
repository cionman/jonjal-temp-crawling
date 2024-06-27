package jonjal.temp.crawling.repository

import jonjal.temp.common.repository.BaseRepository
import jonjal.temp.crawling.entity.AssemblyMemberCurrent

interface AssemblyMemberCurrentRepository : BaseRepository<AssemblyMemberCurrent, Long> {
    fun findBy국회의원코드(code: String?): AssemblyMemberCurrent?
}