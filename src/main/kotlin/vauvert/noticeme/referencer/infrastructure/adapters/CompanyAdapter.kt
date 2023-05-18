package vauvert.noticeme.referencer.infrastructure.adapters

import org.springframework.stereotype.Repository
import vauvert.noticeme.referencer.domain.entity.Company
import vauvert.noticeme.referencer.domain.entity.NewCompany
import vauvert.noticeme.referencer.domain.port.repository.CompanyRepository
import vauvert.noticeme.referencer.domain.utils.CompanyId
import vauvert.noticeme.referencer.infrastructure.database.CompanyDatabaseAccess

@Repository
class CompanyAdapter(private val companyDatabaseAccess: CompanyDatabaseAccess) : CompanyRepository {

    override fun getAllCompanies(): List<Company> = companyDatabaseAccess.getAllCompanies()

    override fun getCompany(companyId: CompanyId): Company =
        requireNotNull(companyDatabaseAccess.getCompany(companyId)) { "There was an issue finding this company" }

    override fun createCompany(company: NewCompany): CompanyId? = companyDatabaseAccess.createCompany(company)

}