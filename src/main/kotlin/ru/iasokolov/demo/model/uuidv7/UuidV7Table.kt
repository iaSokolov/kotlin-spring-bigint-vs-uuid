package ru.iasokolov.demo.model.uuidv7

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import ru.iasokolov.demo.model.TableData
import java.util.UUID

@Entity
@Table(name = "uuid_v7_table")
class UuidV7Table(
    @Id
    var id: UUID,

    var data: TableData
)