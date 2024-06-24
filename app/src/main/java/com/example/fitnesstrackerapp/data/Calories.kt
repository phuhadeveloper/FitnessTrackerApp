package com.example.fitnesstrackerapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "calories")
data class Calories(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Date,
    var caloriesCount: Double
)