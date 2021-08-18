package com.zavanton.company.service

import com.zavanton.company.data.command.CompanyCommand

interface CompanyService {

    fun fetchAllCompanies(): Set<CompanyCommand>

    fun fetchCompanyById(id: Long): CompanyCommand

    fun fetchCompanyByName(name: String): CompanyCommand

    fun createCompany(command: CompanyCommand): CompanyCommand

    fun updateCompany(company: CompanyCommand): CompanyCommand

    fun patchCompany(company: CompanyCommand): CompanyCommand

    fun deleteCompany(id: Long)
}
