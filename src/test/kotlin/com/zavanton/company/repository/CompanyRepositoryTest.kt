package com.zavanton.company.repository

import com.zavanton.company.entity.Company
import org.junit.jupiter.api.Assertions.assertEquals
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

    @Test
    fun `test find by name`() {
        // mock
        val name = "Google"
        val expectedCompany = Company(name = name)
        entityManager.persist(expectedCompany)
        entityManager.flush()

        // action
        val actualCompany = companyRepository.findByName(name)

        // verify
        assertEquals(expectedCompany, actualCompany)
    }
}
