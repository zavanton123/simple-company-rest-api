package com.zavanton.company.service

import com.zavanton.company.command.CompanyCommand
import com.zavanton.company.converter.CompanyCommandToEntityConverter
import com.zavanton.company.converter.CompanyEntityToCommandConverter
import com.zavanton.company.repository.CompanyRepository
import com.zavanton.company.util.CompanyNotFoundException
import org.springframework.stereotype.Service

@Service
class CompanyServiceImpl(
    private val companyRepository: CompanyRepository,
    private val companyEntityToCommandConverter: CompanyEntityToCommandConverter,
    private val companyCommandToEntityConverter: CompanyCommandToEntityConverter
) : CompanyService {

    override fun fetchAllCompanies(): Set<CompanyCommand> {
        return companyRepository.findAll()
            .map { companyEntityToCommandConverter.convert(it) }
            .toSet()
    }

    override fun fetchCompanyById(id: Long): CompanyCommand {
        val companyOptional = companyRepository.findById(id)
        return if (companyOptional.isPresent) {
            companyEntityToCommandConverter.convert(companyOptional.get())
        } else {
            throw CompanyNotFoundException("Company with id $id does not exist")
        }
    }

    override fun fetchCompanyByName(name: String): CompanyCommand {
        val companyOptional = companyRepository.findByName(name)
        return if (companyOptional.isPresent) {
            companyEntityToCommandConverter.convert(companyOptional.get())
        } else {
            throw CompanyNotFoundException("Company with name $name does not exist")
        }
    }

    override fun createCompany(company: CompanyCommand): CompanyCommand {
        val command = companyCommandToEntityConverter.convert(company)
        val savedCompany = companyRepository.save(command)
        return companyEntityToCommandConverter.convert(savedCompany)
    }

    override fun updateCompany(company: CompanyCommand): CompanyCommand {
        val command = companyCommandToEntityConverter.convert(company)
        val savedCompany = companyRepository.save(command)
        return companyEntityToCommandConverter.convert(savedCompany)
    }

    override fun patchCompany(company: CompanyCommand): CompanyCommand {
        // todo zavanton - replace by patch logic
        val command = companyCommandToEntityConverter.convert(company)
        val savedCompany = companyRepository.save(command)
        return companyEntityToCommandConverter.convert(savedCompany)
    }

    override fun deleteCompany(id: Long) {
        companyRepository.deleteById(id)
    }
}
