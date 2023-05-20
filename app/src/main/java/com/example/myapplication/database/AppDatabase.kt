package com.example.myapplication.database

import androidx.room.Database
import com.example.myapplication.model.ModelDatabase
import com.example.myapplication.model.ModelDatabaseJadwal
import androidx.room.RoomDatabase
import com.example.myapplication.database.DatabaseDao


@Database(entities = [ModelDatabase::class, ModelDatabaseJadwal::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun databaseDao(): DatabaseDao?
    abstract fun databaseDao2(): DatabaseDao2?
}

