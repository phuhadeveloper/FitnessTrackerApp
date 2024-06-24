package com.example.fitnesstrackerapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "steps")
data class Steps(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Date,
    var stepsCount: Int
)