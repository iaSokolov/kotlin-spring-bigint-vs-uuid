package ru.iasokolov.demo.model.uuidv4

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import ru.iasokolov.demo.model.TableData
import java.util.UUID

@Entity
@Table(name = "uuid_v4_table")
class UuidV4Table(
    @Id
    var id: UUID,

    var data: TableData
)