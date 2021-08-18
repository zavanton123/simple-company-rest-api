package com.zavanton.company.service

import com.zavanton.company.entity.Company

interface CompanyApiService {

    fun fetchAllCompanies(): Set<Company>
}
