package ru.iasokolov.demo.model.uuidv6

import org.springframework.stereotype.Repository

@Repository
interface UuidV6Repository {
    fun save(model: UuidV6Table): Int
    fun deleteAll(): Int
}