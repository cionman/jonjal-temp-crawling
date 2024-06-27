package jonjal.temp.crawling.repository

import jonjal.temp.common.repository.BaseRepository
import jonjal.temp.crawling.entity.AssemblyCommitteeMember

interface AssemblyCommitteeMemberRepository : BaseRepository<AssemblyCommitteeMember, Long> {
    fun findBy위원회코드And국회의원코드(위원회코드: String?, 국회의원코드: String?): AssemblyCommitteeMember?
}