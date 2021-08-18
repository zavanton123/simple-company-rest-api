package com.zavanton.company.controller.api

import com.zavanton.company.entity.Company
import com.zavanton.company.service.CompanyApiService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class CompanyApiController(
    private val companyApiService: CompanyApiService
) {

    @GetMapping("/companies")
    @ResponseStatus(HttpStatus.OK)
    fun fetchAllCompanies(): Set<Company> {
        return companyApiService.fetchAllCompanies()
    }
}
