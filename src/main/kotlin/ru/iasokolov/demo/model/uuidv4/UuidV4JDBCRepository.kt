package ru.iasokolov.demo.model.uuidv4

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Component

@Component
class UuidV4JDBCRepository(
    private val jdbcTemplate: JdbcTemplate
): UuidV4Repository {
    private val insert = SimpleJdbcInsert(jdbcTemplate).withTableName("uuid_v4_table")

    override fun save(model: UuidV4Table): Int {
        val parameters: MutableMap<String, Any> = HashMap()

        parameters["id"] = model.id
        parameters["firstname"] = model.data.firstname
        parameters["lastname"] = model.data.lastname
        parameters["citizenship"] = model.data.citizenship

        return insert.execute(parameters)
    }

    override fun deleteAll(): Int {
        return jdbcTemplate.update("TRUNCATE TABLE uuid_v4_table")
    }
}