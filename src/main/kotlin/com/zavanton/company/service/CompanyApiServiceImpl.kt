package com.zavanton.company.service

import com.zavanton.company.entity.Company
import com.zavanton.company.repository.CompanyRepository
import org.springframework.stereotype.Service

@Service
class CompanyApiServiceImpl(
    private val companyRepository: CompanyRepository
) : CompanyApiService {

    override fun fetchAllCompanies(): Set<Company> {
        return companyRepository.findAll().toSet()
    }
}
