package com.zavanton.company.controller.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.zavanton.company.controller.ControllerExceptionHandler
import com.zavanton.company.data.dto.CompanyDTO
import com.zavanton.company.data.dto.CompanyListDTO
import com.zavanton.company.service.CompanyApiService
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasSize
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class CompanyApiControllerTest {

    private val companyApiService = mock(CompanyApiService::class.java)
    private val companyApiController = CompanyApiController(companyApiService)
    private val mvc = MockMvcBuilders
        .standaloneSetup(companyApiController)
        .setControllerAdvice(ControllerExceptionHandler())
        .build()

    @Test
    fun fetchAllCompanies() {
        // mock
        val companies = listOf(CompanyDTO(), CompanyDTO())
        val companyListDTO = CompanyListDTO(companies)
        `when`(companyApiService.fetchAllCompanies()).thenReturn(companyListDTO)

        // action
        mvc.perform(
            get("/api/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.companies", hasSize<Any>(2)))

        // verify
        verify(companyApiService).fetchAllCompanies()
    }

    @Test
    fun fetchCompanyById() {
        // mock
        val id = 0L
        val name = "Google"
        val companyDTO = CompanyDTO(id = id, name = name)
        `when`(companyApiService.fetchById(id)).thenReturn(companyDTO)

        // action
        mvc.perform(
            get("/api/companies/$id")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id", equalTo(id.toInt())))
            .andExpect(jsonPath("$.name", equalTo(name)))

        // verify
        verify(companyApiService).fetchById(id)
    }

    @Test
    fun createCompany() {
        // mock
        val id = 0L
        val savedId = 10L
        val name = "Google"
        val companyDTO = CompanyDTO(id = id, name = name)
        val createdCompanyDTO = CompanyDTO(id = savedId, name = name)
        `when`(companyApiService.createCompany(companyDTO)).thenReturn(createdCompanyDTO)

        // action
        mvc.perform(
            post("/api/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(companyDTO))
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id", equalTo(savedId.toInt())))
            .andExpect(jsonPath("$.name", equalTo(name)))

        // verify
        verify(companyApiService).createCompany(companyDTO)
    }

    @Test
    fun updateCompany() {
        // mock
        val id = 0L
        val name = "Google"
        val companyDTO = CompanyDTO(id = id, name = name)
        val updatedCompanyDTO = CompanyDTO(id = id, name = name)
        `when`(companyApiService.updateCompany(companyDTO)).thenReturn(updatedCompanyDTO)

        // action
        mvc.perform(
            put("/api/companies/${id}")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(companyDTO))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id", equalTo(id.toInt())))
            .andExpect(jsonPath("$.name", equalTo(name)))
        // verify
        verify(companyApiService).updateCompany(companyDTO)
    }

    @Test
    fun deleteCompany() {
        // mock
        val id = 0L

        // action
        mvc.perform(delete("/api/companies/$id"))
            .andExpect(status().isNoContent)

        // verify
        verify(companyApiService).deleteCompany(id)
    }
}
