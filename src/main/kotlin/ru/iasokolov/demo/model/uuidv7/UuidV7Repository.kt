package ru.iasokolov.demo.model.uuidv7

import org.springframework.stereotype.Repository

@Repository
interface UuidV7Repository {
    fun save(model: UuidV7Table): Int
    fun deleteAll(): Int
}