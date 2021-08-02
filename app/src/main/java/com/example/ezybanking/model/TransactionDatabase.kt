package com.example.ezybanking.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ezybanking.TransactionDao

@Database(entities = [Transaction::class], version = 1)
abstract class TransactionDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: TransactionDatabase? = null

        fun getInstance(context: Context): TransactionDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): TransactionDatabase {
            return Room.databaseBuilder(context, TransactionDatabase::class.java, "user_database")
                .build()
        }
    }
}
