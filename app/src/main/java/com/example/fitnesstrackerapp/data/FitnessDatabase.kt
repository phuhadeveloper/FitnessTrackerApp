package com.example.fitnesstrackerapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Steps::class, Distance::class, Calories::class], version = 2, exportSchema = false )
@TypeConverters(Converters::class)
abstract class FitnessDatabase : RoomDatabase() {
    abstract fun stepsDao(): StepsDao
    abstract fun distanceDao(): DistanceDao
    abstract fun caloriesDao(): CaloriesDao

    companion object {
        @Volatile
        private  var Instance: FitnessDatabase? = null

        fun getDatabase(context: Context): FitnessDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FitnessDatabase::class.java, "fitness-database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}