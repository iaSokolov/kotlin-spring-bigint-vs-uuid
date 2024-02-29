package ru.iasokolov.demo.model.uuidv6

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import ru.iasokolov.demo.model.TableData
import java.util.UUID

@Entity
@Table(name = "uuid_v6_table")
class UuidV6Table(
    @Id
    var id: UUID,

    var data: TableData
)