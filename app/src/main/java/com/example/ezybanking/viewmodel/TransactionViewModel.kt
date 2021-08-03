package com.example.ezybanking.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import com.example.ezybanking.repository.TransactionRepository
import com.example.ezybanking.model.Transaction
import com.example.ezybanking.data.TransactionDatabase
import kotlinx.coroutines.launch

class TransactionViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Transaction>>
    private val repository: TransactionRepository

    init {
        val transactionDao = TransactionDatabase.getDatabase(application).transactionDao()
        repository = TransactionRepository(transactionDao)
        readAllData = repository.readAllData
    }

    fun addTransaction(transaction: Transaction){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTransaction(transaction)
        }
    }
}
