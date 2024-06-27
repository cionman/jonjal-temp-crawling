package jonjal.temp.crawling.repository

import jonjal.temp.common.repository.BaseRepository
import jonjal.temp.crawling.entity.AssemblyMemberPastHistory

interface AssemblyMemberPastHistoryRepository : BaseRepository<AssemblyMemberPastHistory, Long> {
    fun findBy국회의원코드And경력대수코드And의원이력(국회의원코드: String?, 경력대수코드: String?, 의원이력: String?): AssemblyMemberPastHistory?
}