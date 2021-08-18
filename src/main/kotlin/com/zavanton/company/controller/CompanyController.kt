package com.zavanton.company.controller

import com.zavanton.company.entity.Company
import com.zavanton.company.service.CompanyService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/companies")
class CompanyController(
    private val companyService: CompanyService
) {

    @GetMapping("", "/")
    fun showAllCompanies(
        model: Model
    ): String {
        val companies: Set<Company> = companyService.fetchAllCompanies()
        model.addAttribute("companies", companies)
        return "companies/companies_list"
    }

    @GetMapping("/create")
    fun showCreateCompanyForm(
        model: Model
    ): String {
        val company = Company()
        model.addAttribute("company", company)
        return "companies/create_company"
    }

    @PostMapping("/process_create")
    fun processCreateCompanyForm(
        @ModelAttribute company: Company
    ): String {
        val savedCompany = companyService.createCompany(company)
        return "redirect:/companies/${savedCompany.id}"
    }

    @GetMapping("/{id}")
    fun showCompanyById(
        @PathVariable("id") id: Long,
        model: Model
    ): String {
        val company = companyService.fetchCompanyById(id)
        model.addAttribute("company", company)
        return "companies/company_details"
    }
}
