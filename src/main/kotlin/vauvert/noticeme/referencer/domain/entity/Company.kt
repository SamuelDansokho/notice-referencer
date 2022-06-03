package vauvert.noticeme.referencer.domain.entity

import vauvert.noticeme.referencer.domain.utils.CompanyId
import vauvert.noticeme.referencer.domain.utils.CompanyName

data class Company(
    val companyId: CompanyId,
    val companyName: CompanyName
)