package com.example.calculator.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.calculator.data.database.entity.CurrencyUnit
import com.example.calculator.utils.DB_TABLE

@Dao
interface CurrencyDao{
    @Query("SELECT * FROM $DB_TABLE")
    fun getAll() : List<CurrencyUnit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrency(currencyUnit: CurrencyUnit)
}