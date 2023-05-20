package com.example.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import com.example.myapplication.model.ModelDatabaseJadwal

import androidx.room.OnConflictStrategy
import androidx.room.Query



@Dao
interface DatabaseDao2 {
    @Query("SELECT * FROM tbl_jadwal")
    fun getAllJadwal(): LiveData<List<ModelDatabaseJadwal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDataJadwal(vararg modelDatabases: ModelDatabaseJadwal)

    @Query("DELETE FROM tbl_jadwal WHERE uid= :uid")
    fun deleteJadwalById(uid: Int)

    @Query("DELETE FROM tbl_jadwal")
    fun deleteAlljadwal()
}

