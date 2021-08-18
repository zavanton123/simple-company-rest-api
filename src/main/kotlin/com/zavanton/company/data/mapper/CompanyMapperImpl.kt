package com.zavanton.company.data.mapper

import com.zavanton.company.data.dto.CompanyDTO
import com.zavanton.company.data.entity.Company
import org.springframework.stereotype.Component

@Component
class CompanyMapperImpl : CompanyMapper {

    override fun mapEntityToDto(company: Company): CompanyDTO {
        return CompanyDTO(
            id = company.id,
            name = company.name
        )
    }

    override fun mapDtoToEntity(companyDTO: CompanyDTO): Company {
        return Company(
            id = companyDTO.id,
            name = companyDTO.name
        )
    }

}