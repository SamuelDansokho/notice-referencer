package vauvert.noticeme.referencer.infrastructure.service

import org.springframework.stereotype.Service
import vauvert.noticeme.referencer.domain.entity.Company
import vauvert.noticeme.referencer.domain.entity.CompleteCompany
import vauvert.noticeme.referencer.domain.entity.CompleteTeamMember
import vauvert.noticeme.referencer.domain.utils.CompanyId
import vauvert.noticeme.referencer.infrastructure.adapters.CompanyAdapter
import vauvert.noticeme.referencer.infrastructure.adapters.ContactAdapter
import vauvert.noticeme.referencer.infrastructure.adapters.TeamAdapter

@Service
class CompanyService(
    private val companyAdapter: CompanyAdapter,
    private val teamAdapter: TeamAdapter,
    private val contactAdapter: ContactAdapter
) {

    fun getAllCompanies() = companyAdapter.getAllCompanies().map { CompanyInfo(it.id, it.name, it.description) }

    fun getCompany(companyId: CompanyId): CompleteCompany {
        val company = companyAdapter.getCompany(companyId)
        val companyContact =
            requireNotNull(contactAdapter.getContact(company.id)) { "There was an issue getting contacts for company ${company.id}" }
        val completeTeamMembers = getTeamMembersContact(company)

        return CompleteCompany(company.copy(teamMembers = completeTeamMembers), companyContact)
    }

    private fun getTeamMembersContact(company: Company): List<CompleteTeamMember> {
        val teamMembers = teamAdapter.getTeamMembers(company.id)
        val completeTeamMembers = teamMembers.map {
            CompleteTeamMember(it, contactAdapter.getContact(it.id))
        }
        return completeTeamMembers
    }
}

data class CompanyInfo(
    val id : CompanyId,
    val name : String,
    val description: String

)