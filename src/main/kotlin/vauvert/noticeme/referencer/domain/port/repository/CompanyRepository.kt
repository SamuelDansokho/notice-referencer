package vauvert.noticeme.referencer.domain.port.repository

import vauvert.noticeme.referencer.domain.entity.Company
import vauvert.noticeme.referencer.domain.utils.CompanyId

interface CompanyRepository {

    fun getCompany(companyId: CompanyId): Company
}