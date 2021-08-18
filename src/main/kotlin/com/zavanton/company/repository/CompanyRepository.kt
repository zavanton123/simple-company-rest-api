package com.zavanton.company.repository

import com.zavanton.company.entity.Company
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : CrudRepository<Company, Long> {

    fun findByName(name: String): Company
}
