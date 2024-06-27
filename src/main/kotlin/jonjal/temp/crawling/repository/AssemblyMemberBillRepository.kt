package jonjal.temp.crawling.repository

import jonjal.temp.common.repository.BaseRepository
import jonjal.temp.crawling.entity.AssemblyMemberBill

interface AssemblyMemberBillRepository : BaseRepository<AssemblyMemberBill, Long> {
    fun findBy의안ID(의안ID: String?): AssemblyMemberBill?
}