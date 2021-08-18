package com.zavanton.company.repository

import com.zavanton.company.data.entity.Company
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
internal class CompanyRepositoryTest {

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var companyRepository: CompanyRepository

    @BeforeEach
    fun setup() {
        entityManager.clear()
        entityManager.clear()
        entityManager.flush()
    }

    @Test
    fun `test findByName returns a company if it exists in DB`() {
        // mock
        val name = "TestCompany"
        val expectedCompany = Company(name = name)
        entityManager.persist(expectedCompany)
        entityManager.flush()

        // action
        val actualCompanyOptional = companyRepository.findByName(name)

        // verify
        assertTrue(actualCompanyOptional.isPresent)
        assertEquals(expectedCompany, actualCompanyOptional.get())
    }

    @Test
    fun `test findByName returns an empty optional if the company does not exist in DB`() {
        // mock
        val name = "TestCompany"

        // action
        val actualCompanyOptional = companyRepository.findByName(name)

        // verify
        assertFalse(actualCompanyOptional.isPresent)
    }
}
