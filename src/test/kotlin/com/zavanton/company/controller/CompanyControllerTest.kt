package com.zavanton.company.controller

import com.zavanton.company.entity.Company
import com.zavanton.company.service.CompanyService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
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
        mvc.perform(get("/companies"))
            .andExpect(status().isOk)
            .andExpect(model().attribute("companies", companies))
            .andExpect(view().name("companies/companies_list"))

        // verify
        verify(companyService).fetchAllCompanies()
    }

    @Test
    fun showCreateCompanyForm() {
    }

    @Test
    fun processCreateCompanyForm() {
    }

    @Test
    fun showCompanyById() {
    }
}
