package ru.iasokolov.demo.model.bigint

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import ru.iasokolov.demo.model.TableData

@Entity
@Table(name = "bigint_table")
class BigintTable(
    @Id
    var id: Long,

    var data: TableData
)