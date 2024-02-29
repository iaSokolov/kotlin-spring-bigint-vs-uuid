package ru.iasokolov.demo.model.bigint

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Component

@Component
class BigintJDBCRepository(
    private val jdbcTemplate: JdbcTemplate
): BigintRepository {
    private val insert = SimpleJdbcInsert(jdbcTemplate).withTableName("bigint_table")

    override fun save(model: BigintTable): Int {
        val parameters: MutableMap<String, Any> = HashMap()

        parameters["id"] = model.id
        parameters["firstname"] = model.data.firstname
        parameters["lastname"] = model.data.lastname
        parameters["citizenship"] = model.data.citizenship

        return insert.execute(parameters)
    }

    override fun deleteAll(): Int {
        return jdbcTemplate.update("TRUNCATE TABLE bigint_table")
    }
}