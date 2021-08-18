package com.zavanton.company.converter

import com.zavanton.company.command.CompanyCommand
import com.zavanton.company.entity.Company
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class CompanyEntityToCommandConverter : Converter<Company, CompanyCommand> {

    override fun convert(source: Company): CompanyCommand {
        return CompanyCommand(
            id = source.id,
            name = source.name
        )
    }
}

@Component
class CompanyCommandToEntityConverter : Converter<CompanyCommand, Company> {

    override fun convert(source: CompanyCommand): Company {
        return Company(
            id = source.id,
            name = source.name
        )
    }
}
