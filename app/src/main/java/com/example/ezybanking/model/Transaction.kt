package com.example.ezybanking.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions",
    indices = [Index("creation_date")]
)
data class Transaction(
    @PrimaryKey
    @ColumnInfo(name = "creation_date")
    val creationDate: String,

    @ColumnInfo(name = "amount") val amount: Number?,
    @ColumnInfo(name = "date") val lastName: String?,
    @ColumnInfo(name = "description") val description: String?
)