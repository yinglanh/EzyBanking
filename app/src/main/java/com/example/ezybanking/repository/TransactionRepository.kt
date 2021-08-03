package com.example.ezybanking.repository

import androidx.lifecycle.LiveData
import com.example.ezybanking.data.TransactionDao
import com.example.ezybanking.model.Transaction

class TransactionRepository(private val transactionDao: TransactionDao)  {

    val readAllData: LiveData<List<Transaction>> = transactionDao.getAll()

    suspend fun addTransaction(transaction: Transaction){
        transactionDao.insertTransaction(transaction)
    }
}