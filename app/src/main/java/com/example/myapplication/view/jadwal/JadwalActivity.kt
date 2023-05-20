package com.example.myapplication.view.jadwal

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityJadwalBinding
import com.example.myapplication.model.ModelDatabaseJadwal
import com.example.myapplication.view.jadwal.JadwalAdapter.JadwalAdapterCallback
import com.example.myapplication.viewmodel.JadwalViewModel

class JadwalActivity : AppCompatActivity(), JadwalAdapterCallback {
    private lateinit var binding: ActivityJadwalBinding
    private lateinit var jadwalAdapter: JadwalAdapter
    private lateinit var jadwalViewModel: JadwalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJadwalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUploadData()
        setInitLayout()
        setViewModel()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun setUploadData() {
        binding.btnreset.setOnClickListener {
            val strMK = "KIMIA"
            val strDosen = "Hadafee Mudo"
            val strNIP = "123456789"
            val strNoHP = "080391691822"
            val strTanggal = "19/08/23"
            val strRuang = "E 0303"
            val strJam = "09:00"
            val strPertemuan = "7"

                jadwalViewModel.addDataJadwal(
                    strMK,
                    strDosen,
                    strNIP,
                    strNoHP,
                    strTanggal,
                    strRuang,
                    strJam,
                    strPertemuan)
                Toast.makeText(this@JadwalActivity,
                    "Laporan Anda terkirim, tunggu info selanjutnya ya!", Toast.LENGTH_SHORT).show()
                finish()

        }
    }


    private fun setInitLayout() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.tvNotFound.visibility = View.GONE

        jadwalAdapter = JadwalAdapter(this, mutableListOf(), this)
        binding.rvJadwal.setHasFixedSize(true)
        binding.rvJadwal.layoutManager = LinearLayoutManager(this)
        binding.rvJadwal.adapter = jadwalAdapter
    }

    private fun setViewModel() {
        jadwalViewModel = ViewModelProvider(this).get(JadwalViewModel::class.java)
        jadwalViewModel.dataLaporan.observe(this) { modelDatabases: List<ModelDatabaseJadwal> ->
            if (modelDatabases.isEmpty()) {
                binding.tvNotFound.visibility = View.VISIBLE
                binding.rvJadwal.visibility = View.GONE
            } else {
                binding.tvNotFound.visibility = View.GONE
                binding.rvJadwal.visibility = View.VISIBLE
            }
            jadwalAdapter.setDataAdapter(modelDatabases)
        }
    }

    override fun onDelete(modelDatabase: ModelDatabaseJadwal?) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Hapus riwayat ini?")
        alertDialogBuilder.setPositiveButton("Ya, Hapus") { dialogInterface, i ->
            val uid = modelDatabase?.uid
            uid?.let {
                jadwalViewModel.deleteDataById(it)
                Toast.makeText(
                    this@JadwalActivity, "Yeay! Data yang dipilih sudah dihapus",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        alertDialogBuilder.setNegativeButton("Batal") { dialogInterface: DialogInterface, i:
        Int -> dialogInterface.cancel() }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
