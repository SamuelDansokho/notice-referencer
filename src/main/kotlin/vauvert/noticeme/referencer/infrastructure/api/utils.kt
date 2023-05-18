package vauvert.noticeme.referencer.infrastructure.api

import vauvert.noticeme.referencer.domain.utils.CompanyId

sealed class CompanyCreationResponse {
    class Success(val companyId: CompanyId) : CompanyCreationResponse()
    object Failure : CompanyCreationResponse()
}