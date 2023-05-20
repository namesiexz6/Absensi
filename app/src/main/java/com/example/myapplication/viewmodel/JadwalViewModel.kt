package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.database.DatabaseClient.Companion.getInstance
import com.example.myapplication.database.DatabaseDao
import com.example.myapplication.database.DatabaseDao2
import com.example.myapplication.model.ModelDatabaseJadwal
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers


class JadwalViewModel(application: Application) : AndroidViewModel(application) {
    var databaseDao: DatabaseDao2? = getInstance(application)?.appDatabase?.databaseDao2()
    var dataLaporan: LiveData<List<ModelDatabaseJadwal>>

    fun addDataJadwal(
        mk: String, dosen: String, nip: String,
        nohp: String, tanggal: String, ruang: String, jam: String, pertemuan: String) {
        Completable.fromAction {
            val modelDatabaseJadwal = ModelDatabaseJadwal()
            modelDatabaseJadwal.mk = mk
            modelDatabaseJadwal.dosen = dosen
            modelDatabaseJadwal.nip = nip
            modelDatabaseJadwal.nohp = nohp
            modelDatabaseJadwal.tanggal = tanggal
            modelDatabaseJadwal.ruang = ruang
            modelDatabaseJadwal.jam = jam
            modelDatabaseJadwal.pertemuan = pertemuan
            databaseDao?.insertDataJadwal(modelDatabaseJadwal)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }



    fun deleteDataById(uid: Int) {
        Completable.fromAction {
            databaseDao?.deleteJadwalById(uid)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    init {
        dataLaporan = databaseDao!!.getAllJadwal()
    }

}