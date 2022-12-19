package vauvert.noticeme.referencer.infrastructure.adapters

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import vauvert.noticeme.referencer.domain.entity.Company
import vauvert.noticeme.referencer.infrastructure.database.CompanyDatabaseAccess

class CompanyAdapterTest {

    private val companyDatabaseAccess : CompanyDatabaseAccess = mockk()
    private val companyAdapter = CompanyAdapter(companyDatabaseAccess)
    private val companyName = "Auchan"
    private val companyId = "1"
    private val description = "Some meaningfull text"

    @Test
    fun `should return a company`() {
        // Given
        val expectedCompany = Company(companyId, companyName, description, emptyList())
        every { companyDatabaseAccess.getCompany(any()) } returns expectedCompany

        // When
        val foundCompany = companyAdapter.getCompany(companyId)

        // Then
        assertThat(foundCompany).isEqualTo(expectedCompany)
    }

    @Test
    fun `should return all companies`() {
        // Given
        val inMemoryCompanies = listOf(
            Company("1", "Auchan", "desc1", emptyList()),
            Company("2", "Carrefour", "desc2", emptyList()),
            Company("3", "Score", "desc3", emptyList()),
        )
        every { companyDatabaseAccess.getAllCompanies() } returns inMemoryCompanies

        // When
        val foundCompanies = companyAdapter.getAllCompanies()

        // Then
        assertThat(foundCompanies).isEqualTo(inMemoryCompanies)
    }

}