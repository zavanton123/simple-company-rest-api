package com.zavanton.company.controller

import com.zavanton.company.entity.Company
import com.zavanton.company.service.CompanyService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class CompanyController(
    private val companyService: CompanyService
) {

    companion object {
        const val COMPANIES_URL = "/companies"
        const val COMPANIES_CREATE_URL = "/companies/create"
        const val COMPANIES_PROCESS_CREATE_URL = "/companies/process_create"
        const val COMPANIES_ID_URL = "/companies/{id}"
    }

    @GetMapping(COMPANIES_URL)
    fun showAllCompanies(
        model: Model
    ): String {
        val companies: Set<Company> = companyService.fetchAllCompanies()
        model.addAttribute("companies", companies)
        return "companies/companies_list"
    }

    @GetMapping(COMPANIES_CREATE_URL)
    fun showCreateCompanyForm(
        model: Model
    ): String {
        val company = Company()
        model.addAttribute("company", company)
        return "companies/create_company"
    }

    @PostMapping(COMPANIES_PROCESS_CREATE_URL)
    fun processCreateCompanyForm(
        @ModelAttribute company: Company
    ): String {
        val savedCompany = companyService.createCompany(company)
        return "redirect:/companies/${savedCompany.id}"
    }

    @GetMapping(COMPANIES_ID_URL)
    fun showCompanyById(
        @PathVariable("id") id: Long,
        model: Model
    ): String {
        val company = companyService.fetchCompanyById(id)
        model.addAttribute("company", company)
        return "companies/company_details"
    }
}
