package vauvert.noticeme.referencer.infrastructure.api

import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.ok
import org.springframework.http.ResponseEntity.unprocessableEntity
import org.springframework.web.bind.annotation.*
import vauvert.noticeme.referencer.domain.entity.NewCompany
import vauvert.noticeme.referencer.domain.utils.CompanyId
import vauvert.noticeme.referencer.infrastructure.service.CompanyService

@RestController("/company")
class CompanyController(private val companyService: CompanyService) {

    @GetMapping
    @Operation(summary = "Get all companies")
    fun getAllCompaniesInfos(): ResponseEntity<Any> = ok(companyService.getAllCompanies())


    @GetMapping("/{companyId}")
    @Operation(summary = "Get a company by ID")
    fun getCompany(@PathVariable("companyId") companyId: CompanyId): ResponseEntity<Any> {
        val response = companyService.getCompany(companyId)
        return ok(response)
    }

    @PostMapping
    @Operation(summary = "Create a company")
    fun create(@RequestBody company: NewCompany): ResponseEntity<Any> {
        return when (val response = companyService.createCompany(company)) {
            is CompanyCreationResponse.Success -> ok(response.companyId)
            is CompanyCreationResponse.Failure -> unprocessableEntity().build()
        }
    }

}