package com.zavanton.company.repository

import com.zavanton.company.data.entity.Company
import java.util.*
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : CrudRepository<Company, Long> {

    fun findByName(name: String): Optional<Company>
}
