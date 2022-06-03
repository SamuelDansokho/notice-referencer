package vauvert.noticeme.referencer.infrastructure.service

import org.springframework.stereotype.Service
import vauvert.noticeme.referencer.domain.utils.CompanyId
import vauvert.noticeme.referencer.infrastructure.adapters.CompanyAdapter

@Service
class CompanyService(private val companyAdapter: CompanyAdapter) {

    fun getCompany(companyId: CompanyId) = companyAdapter.getCompany(companyId)
}