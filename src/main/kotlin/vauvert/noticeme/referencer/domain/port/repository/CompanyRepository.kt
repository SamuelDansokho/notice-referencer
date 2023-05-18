package vauvert.noticeme.referencer.domain.port.repository

import vauvert.noticeme.referencer.domain.entity.Company
import vauvert.noticeme.referencer.domain.entity.NewCompany
import vauvert.noticeme.referencer.domain.utils.CompanyId

interface CompanyRepository {

    fun getAllCompanies(): List<Company>

    fun getCompany(companyId: CompanyId): Company?

    fun createCompany(company: NewCompany): CompanyId?
}