package com.zavanton.company.service

import com.zavanton.company.data.command.CompanyCommand
import com.zavanton.company.data.converter.CompanyCommandToEntityConverter
import com.zavanton.company.data.converter.CompanyEntityToCommandConverter
import com.zavanton.company.data.entity.Company
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
import org.mockito.Mockito.never
import org.mockito.Mockito.verify

internal class CompanyServiceImplTest {

    private val companyRepository = mock(CompanyRepository::class.java)
    private val companyEntityToCommandConverter = mock(CompanyEntityToCommandConverter::class.java)
    private val companyCommandToEntityConverter = mock(CompanyCommandToEntityConverter::class.java)
    private val companyService = CompanyServiceImpl(
        companyRepository,
        companyEntityToCommandConverter,
        companyCommandToEntityConverter
    )

    @Test
    fun `test fetchAllCompanies returns a set of all companies from DB`() {
        // mock
        val company1 = Company(id = 0L, name = "Google")
        val company2 = Company(id = 1L, name = "Facebook")
        val companies = listOf(company1, company2)

        val command1 = CompanyCommand()
        val command2 = CompanyCommand()
        val commands = listOf(command1, command2)

        `when`(companyRepository.findAll()).thenReturn(companies)
        `when`(companyEntityToCommandConverter.convert(company1)).thenReturn(command1)
        `when`(companyEntityToCommandConverter.convert(company2)).thenReturn(command2)

        // action
        val actualCompanies = companyService.fetchAllCompanies()

        // verify
        assertTrue(commands.containsAll(actualCompanies))
        assertTrue(actualCompanies.containsAll(commands))
        verify(companyRepository).findAll()
        verify(companyEntityToCommandConverter).convert(company1)
        verify(companyEntityToCommandConverter).convert(company2)
    }

    @Test
    fun `test fetchCompanyById returns a company if it exists in DB`() {
        // mock
        val id = 0L
        val name = "Google"
        val company = Company(id = id, name = name)
        val command = CompanyCommand(id = id, name = name)
        val companyOptional = Optional.of(company)
        `when`(companyRepository.findById(anyLong())).thenReturn(companyOptional)
        `when`(companyEntityToCommandConverter.convert(company)).thenReturn(command)

        // action
        val actualCompany = companyService.fetchCompanyById(id)

        // verify
        verify(companyRepository).findById(anyLong())
        verify(companyEntityToCommandConverter).convert(company)
        assertEquals(command, actualCompany)
    }

    @Test
    fun `test fetchCompanyById throws a not found exception if the company does not exist in DB`() {
        // mock
        val id = 0L
        val name = "Google"
        val company = Company(id = id, name = name)
        val command = CompanyCommand(id = id, name = name)
        val emptyOptional = Optional.empty<Company>()
        `when`(companyEntityToCommandConverter.convert(company)).thenReturn(command)
        `when`(companyRepository.findById(anyLong())).thenReturn(emptyOptional)

        // action
        assertThrows<CompanyNotFoundException> {
            companyService.fetchCompanyById(id)
        }

        // verify
        verify(companyRepository).findById(anyLong())
        verify(companyEntityToCommandConverter, never()).convert(company)
    }

    @Test
    fun `test fetchCompanyByName returns a company if it exists in DB`() {
        // mock
        val name = "Google"
        val company = Company(id = 0, name = name)
        val command = CompanyCommand(id = 0L, name = name)
        val companyOptional = Optional.of(company)
        `when`(companyRepository.findByName(anyString())).thenReturn(companyOptional)
        `when`(companyEntityToCommandConverter.convert(company)).thenReturn(command)

        // action
        val actual = companyService.fetchCompanyByName(name)

        // verify
        verify(companyRepository).findByName(anyString())
        verify(companyEntityToCommandConverter).convert(company)
        assertEquals(command, actual)
    }

    @Test
    fun `test fetchCompanyByName throws a not found exception if the company does not exist in DB`() {
        // mock
        val name = "Google"
        val company = Company()
        val command = CompanyCommand(id = 0L, name = name)
        val emptyOptional = Optional.empty<Company>()
        `when`(companyRepository.findByName(anyString())).thenReturn(emptyOptional)
        `when`(companyEntityToCommandConverter.convert(company)).thenReturn(command)

        // action
        assertThrows<CompanyNotFoundException> {
            companyService.fetchCompanyByName(name)
        }

        // verify
        verify(companyRepository).findByName(anyString())
        verify(companyEntityToCommandConverter, never()).convert(company)
    }

    @Test
    fun `test createCompany saves a company to DB`() {
        // mock
        val id = 0L
        val name = "Google"
        val command = CompanyCommand(id = id, name = name)
        val company = Company(id = id, name = name)
        val savedCompany = Company(id = id, name = name)
        val savedCommand = CompanyCommand(id = id, name = name)
        `when`(companyCommandToEntityConverter.convert(command)).thenReturn(company)
        `when`(companyRepository.save(any(Company::class.java))).thenReturn(savedCompany)
        `when`(companyEntityToCommandConverter.convert(savedCompany)).thenReturn(savedCommand)

        // action
        val actual = companyService.createCompany(command)

        // verify
        verify(companyCommandToEntityConverter).convert(command)
        verify(companyRepository).save(any(Company::class.java))
        verify(companyEntityToCommandConverter).convert(savedCompany)
        assertEquals(command, actual)
    }

    @Test
    fun `test updateCompany updates the company`() {
        // mock
        val id = 0L
        val name = "Google"
        val updatedName = "Amazon"
        val command = CompanyCommand(id = id, name = name)
        val company = Company(id = id, name = name)
        val updatedCompany = Company(id = id, name = updatedName)
        val updatedCommand = CompanyCommand(id = id, name = updatedName)
        `when`(companyCommandToEntityConverter.convert(command)).thenReturn(company)
        `when`(companyRepository.save(any(Company::class.java))).thenReturn(updatedCompany)
        `when`(companyEntityToCommandConverter.convert(updatedCompany)).thenReturn(updatedCommand)

        // action
        val actual = companyService.createCompany(command)

        // verify
        verify(companyCommandToEntityConverter).convert(command)
        verify(companyRepository).save(any(Company::class.java))
        verify(companyEntityToCommandConverter).convert(updatedCompany)
        assertEquals(updatedCommand, actual)
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
