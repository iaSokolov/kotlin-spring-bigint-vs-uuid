package ru.iasokolov.demo.model.statistic

import org.springframework.stereotype.Repository

@Repository
interface StatisticTableRepository {
    fun save(model: StatisticTable): Int

    fun getAvg(): StatisticRecord

    fun deleteAll(): Int
}