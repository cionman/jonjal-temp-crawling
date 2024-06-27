package jonjal.temp.crawling.repository

import jonjal.temp.common.repository.BaseRepository
import jonjal.temp.crawling.entity.AssemblyMemberSns

interface AssemblyMemberSnsRepository : BaseRepository<AssemblyMemberSns, Long> {
    fun findBy국회의원코드(code: String?): AssemblyMemberSns?
}