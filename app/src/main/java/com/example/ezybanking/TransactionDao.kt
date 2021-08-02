package com.example.ezybanking

import androidx.room.*
import com.example.ezybanking.model.Transaction

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions")
    fun getAll(): List<Transaction>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaction: Transaction)

    @Query("SELECT * FROM transactions WHERE creation_date = :creationDate")
    suspend fun getTransactionByDate(creationDate: String): Transaction

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)
}