package vauvert.noticeme.referencer.infrastructure.api

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import vauvert.noticeme.referencer.domain.entity.Company
import vauvert.noticeme.referencer.infrastructure.service.CompanyService

class CompanyControllerTest {

    private val companyService: CompanyService = mockk()
    private val companyController = CompanyController(companyService)

    private val companyName = "Auchan"
    private val companyId = "1"

    @Test
    fun `should return OK with a company`() {
        // Given
        val expectedCompany = Company("1", "Auchan")
        every { companyService.getCompany(any()) } returns Company(companyId, companyName)

        // When
        val response = companyController.getCompany(companyId)

        // Then
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isEqualTo(expectedCompany)
    }

}