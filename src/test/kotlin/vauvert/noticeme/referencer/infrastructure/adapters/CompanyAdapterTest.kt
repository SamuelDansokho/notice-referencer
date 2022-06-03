package vauvert.noticeme.referencer.infrastructure.adapters

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import vauvert.noticeme.referencer.domain.entity.Company

class CompanyAdapterTest {

    private val companyAdapter = CompanyAdapter()
    private val companyName = "Auchan"
    private val companyId = "1"

    @Test
    fun `should return a company`() {
        // Given
        val expectedCompany = Company(companyId, companyName)

        // When
        val foundCompany = companyAdapter.getCompany(companyId)

        // Then
        assertThat(foundCompany).isEqualTo(expectedCompany)
    }

}