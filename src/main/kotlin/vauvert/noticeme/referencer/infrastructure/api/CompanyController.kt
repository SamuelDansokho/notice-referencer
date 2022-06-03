package vauvert.noticeme.referencer.infrastructure.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import vauvert.noticeme.referencer.domain.utils.CompanyId
import vauvert.noticeme.referencer.infrastructure.service.CompanyService

@RestController("/company")
class CompanyController(private val companyService: CompanyService) {

    @GetMapping("/{companyId}")
    fun getCompany(@PathVariable("companyId") companyId: CompanyId): ResponseEntity<Any> {
        val response = companyService.getCompany(companyId)
        return ResponseEntity.ok(response)
    }
}