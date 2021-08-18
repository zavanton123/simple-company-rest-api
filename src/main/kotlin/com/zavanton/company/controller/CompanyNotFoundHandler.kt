package com.zavanton.company.controller

import com.zavanton.company.util.CompanyNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.ModelAndView

@ControllerAdvice
class CompanyNotFoundHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CompanyNotFoundException::class)
    fun handleCompanyNotFoundException(
        exception: CompanyNotFoundException
    ): ModelAndView {
        return ModelAndView("404", mutableMapOf("exception" to exception))
    }
}
