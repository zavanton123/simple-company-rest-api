package com.zavanton.company.service

import com.zavanton.company.entity.Company

interface CompanyService {

    fun fetchAllCompanies(): Set<Company>

    fun fetchCompanyById(id: Long): Company

    fun fetchCompanyByName(name: String): Company

    fun createCompany(company: Company): Company

    fun updateCompany(company: Company): Company

    fun patchCompany(company: Company): Company

    fun deleteCompany(company: Company)
}
