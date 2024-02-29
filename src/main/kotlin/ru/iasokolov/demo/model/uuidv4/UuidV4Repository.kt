package ru.iasokolov.demo.model.uuidv4

import org.springframework.stereotype.Repository

@Repository
interface UuidV4Repository {
    fun save(model: UuidV4Table): Int
    fun deleteAll(): Int
}