package com.zavanton.company.controller.web

import com.zavanton.company.util.CompanyNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.ModelAndView

@ControllerAdvice
class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CompanyNotFoundException::class)
    fun handleCompanyNotFoundException(
        exception: CompanyNotFoundException
    ): ModelAndView {
        return ModelAndView("404", mutableMapOf("exception" to exception))
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleException(): String {
        return "500"
    }
}
