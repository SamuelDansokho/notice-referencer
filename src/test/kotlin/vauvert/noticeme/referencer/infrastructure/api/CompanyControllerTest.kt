package vauvert.noticeme.referencer.infrastructure.api

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import vauvert.noticeme.referencer.domain.entity.*
import vauvert.noticeme.referencer.infrastructure.service.CompanyInfo
import vauvert.noticeme.referencer.infrastructure.service.CompanyService

class CompanyControllerTest {

    private val companyService: CompanyService = mockk()
    private val companyController = CompanyController(companyService)

    private val companyName = "Auchan"
    private val companyId = "100"
    private val email = "companyMail"
    private val address = "somewhere"
    private val phoneNumber = "123456789"
    private val teamMembers = listOf(
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

    @Test
    fun `should return OK with a company`() {
        // Given
        val expectedCompany = CompleteCompany(
            Company("100", "Auchan", "some-description", teamMembers),
            Contact("auchan@referencer.com", "some-adress-auchan", listOf(""))
        )
        every { companyService.getCompany(any()) } returns CompleteCompany(
            Company(
                companyId, companyName, "some-description", teamMembers
            ),
            Contact("auchan@referencer.com", "some-adress-auchan", listOf(""))
        )

        // When
        val response = companyController.getCompany(companyId)

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo(expectedCompany)
    }

    @Test
    fun `should return all companies with OK`() {
        // Given
        val inMemoryCompanies = listOf(
            CompanyInfo("1", "Auchan", "some-description"),
            CompanyInfo("2", "Score", "some-description-1"),
        )
        every { companyService.getAllCompanies() } returns inMemoryCompanies
        // When
        val response = companyController.getAllCompaniesInfos()

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo(inMemoryCompanies)
    }

}