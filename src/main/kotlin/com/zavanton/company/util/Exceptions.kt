package com.zavanton.company.util

class CompanyNotFoundException(message: String = EMPTY) : RuntimeException(message)

class CompanyNotFoundApiException(message: String = EMPTY) : RuntimeException(message)
