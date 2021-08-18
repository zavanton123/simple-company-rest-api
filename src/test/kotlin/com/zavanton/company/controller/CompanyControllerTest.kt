package com.zavanton.company.controller

import com.zavanton.company.controller.CompanyController.Companion.COMPANIES_ATTRIBUTE
import com.zavanton.company.controller.CompanyController.Companion.COMPANIES_CREATE_URL
import com.zavanton.company.controller.CompanyController.Companion.COMPANIES_LIST_TEMPLATE
import com.zavanton.company.controller.CompanyController.Companion.COMPANIES_LIST_URL
import com.zavanton.company.controller.CompanyController.Companion.COMPANIES_PROCESS_CREATE_URL
import com.zavanton.company.controller.CompanyController.Companion.COMPANY_ATTRIBUTE
import com.zavanton.company.controller.CompanyController.Companion.COMPANY_DETAILS_TEMPLATE
import com.zavanton.company.controller.CompanyController.Companion.CREATE_COMPANY_FORM_TEMPLATE
import com.zavanton.company.controller.CompanyController.Companion.DELETE_COMPANY_FORM_TEMPLATE
import com.zavanton.company.entity.Company
import com.zavanton.company.service.CompanyService
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

@WebMvcTest(controllers = [CompanyController::class])
class CompanyControllerTest {

    @MockBean
    lateinit var companyService: CompanyService

    @Autowired
    lateinit var mvc: MockMvc

    @Test
    fun showAllCompanies() {
        // mock
        val companies = setOf(
            Company(id = 0L, name = "Google"),
            Company(id = 1L, name = "Amazon"),
            Company(id = 2L, name = "Netflix"),
        )
        `when`(companyService.fetchAllCompanies()).thenReturn(companies)

        // action
        mvc.perform(get(COMPANIES_LIST_URL))
            .andExpect(status().isOk)
            .andExpect(model().attribute(COMPANIES_ATTRIBUTE, companies))
            .andExpect(view().name(COMPANIES_LIST_TEMPLATE))

        // verify
        verify(companyService).fetchAllCompanies()
    }

    @Test
    fun showCreateCompanyForm() {
        // mock
        val company = Company()

        // action and verify
        mvc.perform(get(COMPANIES_CREATE_URL))
            .andExpect(status().isOk)
            .andExpect(model().attribute(COMPANY_ATTRIBUTE, company))
            .andExpect(view().name(CREATE_COMPANY_FORM_TEMPLATE))
    }

    @Test
    fun processCreateCompanyForm() {
        // mock
        val company = Company(id = 0L, name = "Google")
        val savedCompany = Company(id = 0L, name = "Google")
        `when`(companyService.createCompany(company)).thenReturn(savedCompany)

        // action
        mvc.perform(
            post(COMPANIES_PROCESS_CREATE_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", company.id.toString())
                .param("name", company.name)
        )
            .andExpect(view().name("redirect:/companies/0"))

        // verify
        verify(companyService).createCompany(company)
    }

    @Test
    fun showCompanyById() {
        // mock
        val id = 0L
        val company = Company(id = id, name = "Google")
        `when`(companyService.fetchCompanyById(anyLong())).thenReturn(company)

        // action
        mvc.perform(get("/companies/$id"))
            .andExpect(status().isOk)
            .andExpect(model().attribute(COMPANY_ATTRIBUTE, company))
            .andExpect(view().name(COMPANY_DETAILS_TEMPLATE))

        // verify
        verify(companyService).fetchCompanyById(anyLong())
    }

    @Test
    fun processDeleteCompanyForm() {
        // mock
        val id = 0L
        val company = Company(id = id, name = "Google")
        `when`(companyService.fetchCompanyById(anyLong())).thenReturn(company)

        // action
        mvc.perform(get("/companies/$id/delete"))
            .andExpect(status().isOk)
            .andExpect(model().attribute(COMPANY_ATTRIBUTE, company))
            .andExpect(view().name(DELETE_COMPANY_FORM_TEMPLATE))

        // verify
        verify(companyService).fetchCompanyById(anyLong())
    }
}
