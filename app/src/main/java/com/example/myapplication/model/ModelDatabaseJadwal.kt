package com.example.myapplication.model

import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Entity
import java.io.Serializable



@Entity(tableName = "tbl_jadwal")
class ModelDatabaseJadwal : Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    var uid = 0

    @ColumnInfo(name = "mk")
    lateinit var mk: String

    @ColumnInfo(name = "dosen")
    lateinit var dosen: String

    @ColumnInfo(name = "nip")
    lateinit var nip: String

    @ColumnInfo(name = "nohp")
    lateinit var nohp: String

    @ColumnInfo(name = "tanggal")
    lateinit var tanggal: String

    @ColumnInfo(name = "ruang")
    lateinit var ruang: String

    @ColumnInfo(name = "jam")
    lateinit var jam: String

    @ColumnInfo(name = "pertemuan")
    lateinit var pertemuan: String
}
