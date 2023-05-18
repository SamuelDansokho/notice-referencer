package vauvert.noticeme.referencer.domain.entity

import vauvert.noticeme.referencer.domain.utils.CompanyId
import vauvert.noticeme.referencer.domain.utils.CompanyName

data class Company(
    val id: CompanyId,
    val name: CompanyName,
    val description: String,
    val teamMembers: List<CompleteTeamMember>? = emptyList()
)

data class CompleteCompany(
    val company: Company,
    val companyContact: Contact
)

data class NewCompany(
    val name: CompanyName,
    val description: String
)