package com.zavanton.company.controller.api

import com.zavanton.company.data.dto.CompanyDTO
import com.zavanton.company.data.dto.CompanyListDTO
import com.zavanton.company.service.CompanyApiService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
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
    fun fetchAllCompanies(): CompanyListDTO {
        return companyApiService.fetchAllCompanies()
    }

    @GetMapping("/companies/{id}")
    fun fetchCompanyById(
        @PathVariable("id") id: Long
    ): CompanyDTO {
        return companyApiService.fetchById(id)
    }

    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCompany(
        @RequestBody companyDTO: CompanyDTO
    ): CompanyDTO {
        return companyApiService.createCompany(companyDTO)
    }

    @PutMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateCompany(
        @PathVariable("id") id: Long,
        @RequestBody companyDto: CompanyDTO
    ): CompanyDTO {
        companyDto.id = id
        return companyApiService.updateCompany(companyDto)
    }


    @DeleteMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCompany(
        @PathVariable("id") id: Long
    ) {
        return companyApiService.deleteCompany(id)
    }

}
