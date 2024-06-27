package jonjal.temp.crawling.repository

import jonjal.temp.common.repository.BaseRepository
import jonjal.temp.crawling.entity.AssemblyMemberCurrentHistory

interface AssemblyMemberCurrentHistoryRepository : BaseRepository<AssemblyMemberCurrentHistory, Long> {
    fun findBy국회의원코드And대수코드And의원이력(국회의원코드: String?, 대수코드: String?, 의원이력: String?): AssemblyMemberCurrentHistory?
}