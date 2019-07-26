package com.example.calculator.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.calculator.data.database.dao.CurrencyDao
import com.example.calculator.data.database.entity.CurrencyUnit

@Database(entities = [CurrencyUnit::class], version = 1, exportSchema = true)
abstract class CurrencyDatabase : RoomDatabase(){
    abstract fun currencyDAO(): CurrencyDao
//
//    companion object {
//
//        @Volatile private var INSTANCE: CurrencyDatabase? = null
//
//        fun getInstance(context: Context): CurrencyDatabase =
//            INSTANCE ?: synchronized(this) {
//                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
//            }
//
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(context.applicationContext,
//                CurrencyDatabase::class.java, DB_NAME)
//                .build()
//    }
}