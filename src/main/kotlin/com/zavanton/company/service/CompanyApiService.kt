package com.zavanton.company.service

import com.zavanton.company.data.dto.CompanyDTO
import com.zavanton.company.data.dto.CompanyListDTO

interface CompanyApiService {

    fun fetchAllCompanies(): CompanyListDTO

    fun fetchById(id: Long): CompanyDTO

    fun createCompany(companyDTO: CompanyDTO): CompanyDTO

    fun updateCompany(companyDTO: CompanyDTO): CompanyDTO

    fun deleteCompany(id: Long)
}
