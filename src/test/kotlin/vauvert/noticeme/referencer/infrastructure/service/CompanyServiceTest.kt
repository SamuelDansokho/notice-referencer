package vauvert.noticeme.referencer.infrastructure.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.verifyAll
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import vauvert.noticeme.referencer.domain.entity.*
import vauvert.noticeme.referencer.infrastructure.adapters.CompanyAdapter
import vauvert.noticeme.referencer.infrastructure.adapters.ContactAdapter
import vauvert.noticeme.referencer.infrastructure.adapters.TeamAdapter

class CompanyServiceTest {

    private val companyAdapter: CompanyAdapter = mockk()
    private val contactAdapter: ContactAdapter = mockk()
    private val teamAdapter: TeamAdapter = mockk()
    private val companyService = CompanyService(companyAdapter, teamAdapter, contactAdapter)
    private val companyName = "Auchan"
    private val companyId = "100"
    private val description = "Some meaningfull text"

    @Test
    fun `should call adapter to find a given company and decorate it with appropriate members and contact infos`() {
        // Given
        withTeamMembers()
        withContacts()
        val expectedCompany = CompleteCompany(
            Company(
                "100", "Auchan", description, listOf(
                    CompleteTeamMember(
                        TeamMember(
                            "1",
                            "1",
                            "Lautaro Martinez",
                            TeamMemberRole.LEADER
                        ),
                        Contact(
                            "lautaro.martinez@referencer.com",
                            "some-address",
                            listOf("0123456789", "9876543210")
                        )
                    ),
                    CompleteTeamMember(
                        TeamMember(
                            "2",
                            "2",
                            "Nicolo Barella",
                            TeamMemberRole.LEADER
                        ),
                        Contact(
                            "nicolo.barella@referencer.com",
                            "some-address-2",
                            listOf("123456789", "987654321")
                        )
                    )
                )
            ),
            Contact("auchan@referencer.com", "some-adress-auchan", listOf(""))
        )
        every { companyAdapter.getCompany(any()) } returns Company(companyId, companyName, description, emptyList())

        // When
        val foundCompany = companyService.getCompany(companyId)

        // Then
        assertThat(foundCompany).isEqualTo(expectedCompany)
        verifyAll {
            companyAdapter.getCompany(companyId)
            teamAdapter.getTeamMembers("100")
            contactAdapter.getContact("1")
            contactAdapter.getContact("2")
            contactAdapter.getContact("100")
        }
    }

    @Test
    fun `should call adapter to find companies`() {
        // Given
        val inMemoryCompanies = listOf(
            Company("1", "Auchan", "desc1", emptyList()),
            Company("2", "Carrefour", "desc2", emptyList()),
            Company("3", "Score", "desc3", emptyList()),
        )
        val expectedCompaniesInfo = listOf(
            CompanyInfo("1", "Auchan", "desc1",),
            CompanyInfo("2", "Carrefour", "desc2",),
            CompanyInfo("3", "Score", "desc3",),
        )

        every { companyAdapter.getAllCompanies() } returns inMemoryCompanies

        // When
        val foundCompanies = companyService.getAllCompanies()

        // Then
        assertThat(foundCompanies).isEqualTo(expectedCompaniesInfo)
        verify { companyAdapter.getAllCompanies() }
    }

    private fun withTeamMembers() {
        every { teamAdapter.getTeamMembers(any()) } returns listOf(
            TeamMember(
                "1",
                "1",
                "Lautaro Martinez",
                TeamMemberRole.LEADER
            ),
            TeamMember(
                "2",
                "2",
                "Nicolo Barella",
                TeamMemberRole.LEADER
            ),
        )
    }

    private fun withContacts() {
        every { contactAdapter.getContact("1") } returns Contact(
            "lautaro.martinez@referencer.com",
            "some-address",
            listOf("0123456789", "9876543210")
        )
        every { contactAdapter.getContact("2") } returns Contact(
            "nicolo.barella@referencer.com",
            "some-address-2",
            listOf("123456789", "987654321")
        )
        every { contactAdapter.getContact("100") } returns Contact(
            "auchan@referencer.com",
            "some-adress-auchan",
            listOf("")
        )
    }
}