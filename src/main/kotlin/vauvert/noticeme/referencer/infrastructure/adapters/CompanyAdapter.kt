package vauvert.noticeme.referencer.infrastructure.adapters

import org.springframework.stereotype.Repository
import vauvert.noticeme.referencer.domain.entity.Company
import vauvert.noticeme.referencer.domain.port.repository.CompanyRepository
import vauvert.noticeme.referencer.domain.utils.CompanyId

@Repository
class CompanyAdapter : CompanyRepository {

    override fun getCompany(companyId: CompanyId): Company {
        return Company("1", "Auchan")
    }
}