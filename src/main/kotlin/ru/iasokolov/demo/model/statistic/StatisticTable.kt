package ru.iasokolov.demo.model.statistic

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "statistic_table")
class StatisticTable(
    @Id
    var id: Int,

    @Column(name = "bigint_table")
    var bigintId: Long,

    @Column(name = "uuid_v4_table")
    var uuidV4Id: Long,

    @Column(name = "uuid_v6_table")
    var uuidV6Id: Long,

    @Column(name = "uuid_v7_table")
    var uuidV7Id: Long
)