package com.example.myapplication.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.utils.SessionLogin
import com.example.myapplication.view.absen.AbsenActivity
import com.example.myapplication.view.history.HistoryActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.view.jadwal.JadwalActivity
import com.example.myapplication.view.jadwal.JadwalAdapter

class MainActivity : AppCompatActivity() {

    lateinit var strTitle: String
    lateinit var session: SessionLogin
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setInitLayout()
    }

    private fun setInitLayout() {
        session = SessionLogin(this)
        session.checkLogin()

        binding.cvJadwal.setOnClickListener {
            val intent = Intent(this@MainActivity, JadwalActivity::class.java)
            startActivity(intent)
        }

        binding.cvAbsenMasuk.setOnClickListener {
            strTitle = "Absen Masuk"
            val intent = Intent(this@MainActivity, AbsenActivity::class.java)
            intent.putExtra(AbsenActivity.DATA_TITLE, strTitle)
            startActivity(intent)
        }


        binding.cvHistory.setOnClickListener {
            val intent = Intent(this@MainActivity, HistoryActivity::class.java)
            startActivity(intent)
        }

        binding.imageLogout.setOnClickListener {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setMessage("Yakin Anda ingin Logout?")
            builder.setCancelable(true)
            builder.setNegativeButton("Batal") { dialog, which -> dialog.cancel() }
            builder.setPositiveButton("Ya") { dialog, which ->
                session.logoutUser()
                finishAffinity()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }
}
