package ru.iasokolov.demo.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class TableData(
    @Column(name = "firstname")
    var firstname: String,

    @Column(name = "lastname")
    var lastname: String,

    @Column(name = "citizenship")
    var citizenship: String
)