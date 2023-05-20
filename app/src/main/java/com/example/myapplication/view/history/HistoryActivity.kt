package com.example.myapplication.view.history

import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityHistoryBinding
import com.example.myapplication.model.ModelDatabase
import com.example.myapplication.view.history.HistoryAdapter.HistoryAdapterCallback
import com.example.myapplication.viewmodel.HistoryViewModel

class HistoryActivity : AppCompatActivity(), HistoryAdapterCallback {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var historyViewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setInitLayout()
        setViewModel()
    }

    private fun setInitLayout() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.tvNotFound.visibility = View.GONE

        historyAdapter = HistoryAdapter(this, mutableListOf(), this)
        binding.rvHistory.setHasFixedSize(true)
        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        binding.rvHistory.adapter = historyAdapter
    }

    private fun setViewModel() {
        historyViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        historyViewModel.dataLaporan.observe(this) { modelDatabases: List<ModelDatabase> ->
            if (modelDatabases.isEmpty()) {
                binding.tvNotFound.visibility = View.VISIBLE
                binding.rvHistory.visibility = View.GONE
            } else {
                binding.tvNotFound.visibility = View.GONE
                binding.rvHistory.visibility = View.VISIBLE
            }
            historyAdapter.setDataAdapter(modelDatabases)
        }
    }

    override fun onDelete(modelDatabase: ModelDatabase) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("Hapus riwayat ini?")
        alertDialogBuilder.setPositiveButton("Ya, Hapus") { dialogInterface, i ->
            val uid = modelDatabase?.uid
            uid?.let {
                historyViewModel.deleteDataById(it)
                Toast.makeText(
                    this@HistoryActivity, "Yeay! Data yang dipilih sudah dihapus",
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
