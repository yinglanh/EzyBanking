package com.example.ezybanking.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ezybanking.model.Transaction

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions ORDER BY id ASC")
    fun getAll(): LiveData<List<Transaction>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaction: Transaction)
}