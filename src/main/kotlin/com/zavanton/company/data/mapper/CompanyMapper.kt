package com.zavanton.company.data.mapper

import com.zavanton.company.data.dto.CompanyDTO
import com.zavanton.company.data.entity.Company

interface CompanyMapper {

    fun mapEntityToDto(company: Company): CompanyDTO
    fun mapDtoToEntity(companyDTO: CompanyDTO): Company
}
