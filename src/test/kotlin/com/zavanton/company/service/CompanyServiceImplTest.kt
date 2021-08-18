package com.zavanton.company.service

import com.zavanton.company.entity.Company
import com.zavanton.company.repository.CompanyRepository
import com.zavanton.company.util.CompanyNotFoundException
import java.util.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

internal class CompanyServiceImplTest {

    private val companyRepository = mock(CompanyRepository::class.java)
    private val companyService = CompanyServiceImpl(companyRepository)

    @Test
    fun `test fetchAllCompanies returns a set of all companies from DB`() {
        // mock
        val companies = listOf(
            Company(name = "Google"),
            Company(name = "Facebook")
        )
        `when`(companyRepository.findAll()).thenReturn(companies)
        // action
        val actualCompanies = companyService.fetchAllCompanies()

        // verify
        assertTrue(companies.containsAll(actualCompanies))
        assertTrue(actualCompanies.containsAll(companies))
    }

    @Test
    fun `test fetchCompanyById returns a company if it exists in DB`() {
        // mock
        val id = 0L
        val company = Company(id = id, name = "Google")
        val companyOptional = Optional.of(company)
        `when`(companyRepository.findById(anyLong())).thenReturn(companyOptional)

        // action
        val actualCompany = companyService.fetchCompanyById(id)

        // verify
        verify(companyRepository).findById(anyLong())
        assertEquals(company, actualCompany)
    }

    @Test
    fun `test fetchCompanyById throws a not found exception if the company does not exist in DB`() {
        // mock
        val id = 0L
        val emptyOptional = Optional.empty<Company>()
        `when`(companyRepository.findById(anyLong())).thenReturn(emptyOptional)

        // action
        assertThrows<CompanyNotFoundException> {
            companyService.fetchCompanyById(id)
        }

        // verify
        verify(companyRepository).findById(anyLong())
    }

    @Test
    fun `test fetchCompanyByName returns a company if it exists in DB`() {
        // mock
        val name = "Google"
        val company = Company(id = 0, name = name)
        val companyOptional = Optional.of(company)
        `when`(companyRepository.findByName(anyString())).thenReturn(companyOptional)

        // action
        val actualCompany = companyService.fetchCompanyByName(name)

        verify(companyRepository).findByName(anyString())
        // verify
        assertEquals(company, actualCompany)
    }

    @Test
    fun `test fetchCompanyByName throws a not found exception if the company does not exist in DB`() {
        // mock
        val name = "Google"
        val emptyOptional = Optional.empty<Company>()
        `when`(companyRepository.findByName(anyString())).thenReturn(emptyOptional)

        // action
        assertThrows<CompanyNotFoundException> {
            companyService.fetchCompanyByName(name)
        }

        // verify
        verify(companyRepository).findByName(anyString())
    }

    @Test
    fun `test createCompany saves a company to DB`() {
        // mock
        val company = Company()
        `when`(companyRepository.save(any(Company::class.java))).thenReturn(company)

        // action
        val actualCompany = companyService.createCompany(company)

        // verify
        verify(companyRepository).save(company)
        assertEquals(company, actualCompany)
    }

    @Test
    fun `test updateCompany updates the company`() {
        // mock
        val company = Company(id = 0L, name = "Google")
        val updatedCompany = Company(id = 0L, name = "Amazon")
        `when`(companyRepository.save(company)).thenReturn(updatedCompany)

        // action
        val actualCompany = companyService.updateCompany(company)

        // verify
        verify(companyRepository).save(company)
        assertEquals(updatedCompany, actualCompany)
    }

    @Test
    fun `test patchCompany updates the company`() {
        // mock
        val company = Company(id = 0L, name = "Google")
        val updatedCompany = Company(id = 0L, name = "Amazon")
        `when`(companyRepository.save(company)).thenReturn(updatedCompany)

        // action
        val actualCompany = companyService.patchCompany(company)

        // verify
        verify(companyRepository).save(company)
        assertEquals(updatedCompany, actualCompany)
    }

    @Test
    fun `test deleteCompany deletes the company`() {
        // mock
        val company = Company(id = 0L, name = "Google")

        // action
        companyService.deleteCompany(company.id)

        // verify
        verify(companyRepository).deleteById(company.id)
    }
}
