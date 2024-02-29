package ru.iasokolov.demo.model.uuidv7

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Component

@Component
class UuidV7JDBCRepository(
    private val jdbcTemplate: JdbcTemplate
): UuidV7Repository {
    private val insert = SimpleJdbcInsert(jdbcTemplate).withTableName("uuid_v7_table")

    override fun save(model: UuidV7Table): Int {
        val parameters: MutableMap<String, Any> = HashMap()

        parameters["id"] = model.id
        parameters["firstname"] = model.data.firstname
        parameters["lastname"] = model.data.lastname
        parameters["citizenship"] = model.data.citizenship

        return insert.execute(parameters)
    }

    override fun deleteAll(): Int {
        return jdbcTemplate.update("TRUNCATE TABLE uuid_v7_table")
    }
}