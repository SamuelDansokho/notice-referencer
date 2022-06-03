package vauvert.noticeme.referencer.infrastructure.service

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import vauvert.noticeme.referencer.domain.entity.Company
import vauvert.noticeme.referencer.infrastructure.adapters.CompanyAdapter

class CompanyServiceTest {

    private val companyAdapter: CompanyAdapter = mockk()
    private val companyService = CompanyService(companyAdapter)
    private val companyName = "Auchan"
    private val companyId = "1"

    @Test
    fun `should return a company`() {
        // Given
        val expectedCompany = Company("1", "Auchan")
        every { companyAdapter.getCompany(any()) } returns Company(companyId, companyName)

        // When
        val foundCompany = companyService.getCompany(companyId)

        // Then
        assertThat(foundCompany).isEqualTo(expectedCompany)
    }
}