package com.example.calculator.data.database

import androidx.room.Entity
import com.example.calculator.utils.DB_TABLE
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["name"], tableName = DB_TABLE)
data class CurrencyUnit (
    @field:SerializedName("name")
    val name : String,
    @field:SerializedName("price")
    val price : String
)