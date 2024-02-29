package ru.iasokolov.demo.model.bigint

import org.springframework.stereotype.Repository

@Repository
interface BigintRepository {
    fun save(model: BigintTable): Int

    fun deleteAll(): Int
}