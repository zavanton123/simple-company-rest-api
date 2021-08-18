package com.zavanton.company.service

import com.zavanton.company.data.dto.CompanyDTO
import com.zavanton.company.data.dto.CompanyListDTO
import com.zavanton.company.data.mapper.CompanyMapper
import com.zavanton.company.repository.CompanyRepository
import com.zavanton.company.util.CompanyNotFoundApiException
import org.springframework.stereotype.Service

@Service
class CompanyApiServiceImpl(
    private val companyRepository: CompanyRepository,
    private val mapper: CompanyMapper
) : CompanyApiService {

    override fun fetchAllCompanies(): CompanyListDTO {
        return CompanyListDTO(companyRepository.findAll()
            .map { mapper.mapEntityToDto(it) })
    }

    override fun fetchById(id: Long): CompanyDTO {
        val companyOptional = companyRepository.findById(id)
        return if (companyOptional.isPresent) {
            mapper.mapEntityToDto(companyOptional.get())
        } else {
            throw CompanyNotFoundApiException()
        }
    }

    override fun createCompany(companyDTO: CompanyDTO): CompanyDTO {
        val company = mapper.mapDtoToEntity(companyDTO)
        val savedCompany = companyRepository.save(company)
        return mapper.mapEntityToDto(savedCompany)
    }

    override fun updateCompany(companyDTO: CompanyDTO): CompanyDTO {
        val company = mapper.mapDtoToEntity(companyDTO)
        val savedCompany = companyRepository.save(company)
        return mapper.mapEntityToDto(savedCompany)
    }

    override fun deleteCompany(id: Long) {
        companyRepository.deleteById(id)
    }
}
