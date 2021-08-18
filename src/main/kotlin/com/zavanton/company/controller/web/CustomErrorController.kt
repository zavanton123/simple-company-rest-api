package com.zavanton.company.controller.web

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus

@Controller
class CustomErrorController : ErrorController {

    companion object {
        const val ERROR_URL = "/error"
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @RequestMapping(ERROR_URL)
    fun processGenericError(): String {
        return "error"
    }
}
