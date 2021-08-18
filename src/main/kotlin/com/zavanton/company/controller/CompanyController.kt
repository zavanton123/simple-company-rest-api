package com.zavanton.company.controller

import com.zavanton.company.entity.Company
import com.zavanton.company.service.CompanyService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
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
}
