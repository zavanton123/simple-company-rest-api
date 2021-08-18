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
        const val ID_PATH_VARIABLE = "id"

        const val COMPANIES_ATTRIBUTE = "companies"
        const val COMPANY_ATTRIBUTE = "company"

        const val REDIRECT_TO = "redirect:"
        const val COMPANIES_LIST_URL = "/companies"
        const val COMPANIES_CREATE_URL = "/companies/create"
        const val COMPANIES_PROCESS_CREATE_URL = "/companies/process_create"
        const val COMPANIES_ID_URL = "/companies/{id}"
        const val DELETE_COMPANY_FORM_URL = "/companies/{id}/delete"
        const val PROCESS_DELETE_COMPANY_URL = "/companies/process_delete"

        const val COMPANIES_LIST_TEMPLATE = "companies/companies_list"
        const val COMPANY_DETAILS_TEMPLATE = "companies/company_details"
        const val CREATE_COMPANY_FORM_TEMPLATE = "companies/create_company"
        const val DELETE_COMPANY_FORM_TEMPLATE = "companies/delete_company"
    }

    @GetMapping(COMPANIES_LIST_URL)
    fun showAllCompanies(
        model: Model
    ): String {
        val companies: Set<Company> = companyService.fetchAllCompanies()
        model.addAttribute(COMPANIES_ATTRIBUTE, companies)
        return COMPANIES_LIST_TEMPLATE
    }

    @GetMapping(COMPANIES_CREATE_URL)
    fun showCreateCompanyForm(
        model: Model
    ): String {
        val company = Company()
        model.addAttribute(COMPANY_ATTRIBUTE, company)
        return CREATE_COMPANY_FORM_TEMPLATE
    }

    @PostMapping(COMPANIES_PROCESS_CREATE_URL)
    fun processCreateCompanyForm(
        @ModelAttribute company: Company
    ): String {
        val savedCompany = companyService.createCompany(company)
        return REDIRECT_TO + COMPANIES_LIST_URL + "/${savedCompany.id}"
    }

    @GetMapping(COMPANIES_ID_URL)
    fun showCompanyById(
        @PathVariable(ID_PATH_VARIABLE) id: Long,
        model: Model
    ): String {
        val company = companyService.fetchCompanyById(id)
        model.addAttribute(COMPANY_ATTRIBUTE, company)
        return COMPANY_DETAILS_TEMPLATE
    }

    @GetMapping(DELETE_COMPANY_FORM_URL)
    fun showDeleteCompanyForm(
        @PathVariable(ID_PATH_VARIABLE) id: Long,
        model: Model
    ): String {
        val company = companyService.fetchCompanyById(id)
        model.addAttribute(COMPANY_ATTRIBUTE, company)
        return DELETE_COMPANY_FORM_TEMPLATE
    }

    @PostMapping(PROCESS_DELETE_COMPANY_URL)
    fun processDeleteCompanyForm(
        @ModelAttribute company: Company
    ): String {
        companyService.deleteCompany(company.id)
        return REDIRECT_TO + COMPANIES_LIST_URL
    }
}










