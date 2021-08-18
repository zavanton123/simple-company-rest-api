package com.zavanton.company.controller

import com.zavanton.company.controller.web.IndexController
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view
import org.springframework.test.web.servlet.setup.MockMvcBuilders

internal class IndexControllerTest {

    private val indexController = IndexController()

    private val mvc = MockMvcBuilders
        .standaloneSetup(indexController)
        .build()

    @Test
    fun `test get request to root returns an index page`() {
        mvc.perform(get("/"))
            .andExpect(view().name("index"))
    }
}
