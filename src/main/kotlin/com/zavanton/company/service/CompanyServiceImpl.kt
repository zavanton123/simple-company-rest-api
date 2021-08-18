package com.zavanton.company.service

import com.zavanton.company.entity.Company
import com.zavanton.company.repository.CompanyRepository
import com.zavanton.company.util.CompanyNotFoundException
import org.springframework.stereotype.Service

@Service
class CompanyServiceImpl(
    private val companyRepository: CompanyRepository
) : CompanyService {

    override fun fetchAllCompanies(): Set<Company> {
        return companyRepository.findAll().toSet()
    }

    override fun fetchCompanyById(id: Long): Company {
        val companyOptional = companyRepository.findById(id)
        return if (companyOptional.isPresent) {
            companyOptional.get()
        } else {
            throw CompanyNotFoundException("Company with id $id does not exist")
        }
    }

    override fun fetchCompanyByName(name: String): Company {
        val companyOptional = companyRepository.findByName(name)
        return if (companyOptional.isPresent) {
            companyOptional.get()
        } else {
            throw CompanyNotFoundException("Company with name $name does not exist")
        }
    }

    override fun createCompany(company: Company): Company {
        return companyRepository.save(company)
    }

    override fun updateCompany(company: Company): Company {
        return companyRepository.save(company)
    }

    override fun patchCompany(company: Company): Company {
        // todo zavanton - replace by patch logic
        return companyRepository.save(company)
    }

    override fun deleteCompany(company: Company) {
        companyRepository.delete(company)
    }
}
