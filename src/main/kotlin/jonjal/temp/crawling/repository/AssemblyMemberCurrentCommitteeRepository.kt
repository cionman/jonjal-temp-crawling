package jonjal.temp.crawling.repository

import jonjal.temp.common.repository.BaseRepository
import jonjal.temp.crawling.entity.AssemblyMemberCurrentCommittee

interface AssemblyMemberCurrentCommitteeRepository : BaseRepository<AssemblyMemberCurrentCommittee, Long> {
    fun findBy국회의원코드And경력대수코드And위원회경력(국회의원코드: String?, 경력대수코드: String?, 위원회경력: String?): AssemblyMemberCurrentCommittee?
}