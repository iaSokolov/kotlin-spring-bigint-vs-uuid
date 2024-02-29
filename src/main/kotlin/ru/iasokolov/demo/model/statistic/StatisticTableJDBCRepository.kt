package ru.iasokolov.demo.model.statistic

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Component

@Component
class StatisticTableJDBCRepository(
    private val jdbcTemplate: JdbcTemplate
) : StatisticTableRepository {
    private val insert = SimpleJdbcInsert(jdbcTemplate).withTableName("statistic_table")

    override fun save(model: StatisticTable): Int {
        val parameters: MutableMap<String, Any> = HashMap()

        parameters["id"] = model.id
        parameters["bigint_table"] = model.bigintId
        parameters["uuid_v4_table"] = model.uuidV4Id
        parameters["uuid_v6_table"] = model.uuidV6Id
        parameters["uuid_v7_table"] = model.uuidV7Id

        return insert.execute(parameters)
    }

    override fun getAvg(): StatisticRecord {
        val sql = """
            select 
                avg(bigint_table) as bigint_table, 
                avg(uuid_v4_table) as uuid_v4_table, 
                avg(uuid_v6_table) as uuid_v6_table, 
                avg(uuid_v7_table) as uuid_v7_table 
            from statistic_table
        """
        val res = jdbcTemplate.queryForMap(sql)
        val bigint = res["bigint_table"]
        val uuidV4 = res["uuid_v4_table"]
        val uuidV6 = res["uuid_v6_table"]
        val uuidV7 = res["uuid_v7_table"]

        return StatisticRecord(
            bigint = bigint?.toString()?.toDouble() ?: 0.0,
            uuidV4 = uuidV4?.toString()?.toDouble() ?: 0.0,
            uuidV6 = uuidV6?.toString()?.toDouble() ?: 0.0,
            uuidV7 = uuidV7?.toString()?.toDouble() ?: 0.0
        )
    }

    override fun deleteAll(): Int {
        return jdbcTemplate.update("TRUNCATE TABLE statistic_table")
    }
}