package com.zavanton.company.command

import com.zavanton.company.util.EMPTY

data class CompanyCommand(
    val id: Long = 0L,
    val name: String = EMPTY
)
