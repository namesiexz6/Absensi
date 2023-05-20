package com.example.myapplication.view.jadwal

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.ModelDatabase
import com.example.myapplication.R
import com.example.myapplication.databinding.ListJadwalBinding
import com.example.myapplication.model.ModelDatabaseJadwal

class JadwalAdapter(
    private val mContext: Context,
    private val modelDatabase: MutableList<ModelDatabaseJadwal>,
    private val mAdapterCallback: JadwalAdapterCallback
) : RecyclerView.Adapter<JadwalAdapter.ViewHolder>() {

    fun setDataAdapter(items: List<ModelDatabaseJadwal>) {
        modelDatabase.clear()
        modelDatabase.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListJadwalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelDatabase[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return modelDatabase.size
    }

    inner class ViewHolder(private val binding: ListJadwalBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModelDatabaseJadwal) {
            binding.tvDosen.text = data.dosen
            binding.tvNIP.text = data.nip
            binding.tvNoHP.text = data.nohp
            binding.tvJadwalDate.text = data.tanggal
            binding.tvJadwalRuang.text = data.ruang
            binding.tvJadwalTime.text = data.jam
            binding.tvPertemuan.text = data.pertemuan




            when (data.uid % 2 == 1) {
                true -> {
                    binding.colorStatus.setBackgroundResource(R.drawable.bg_circle_radius)
                    binding.colorStatus.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                }
                false -> {
                    binding.colorStatus.setBackgroundResource(R.drawable.bg_circle_radius)
                    binding.colorStatus.backgroundTintList = ColorStateList.valueOf(Color.RED)
                }
            }


            binding.cvJadwal.setOnClickListener {
                val modelLaundry = modelDatabase[adapterPosition]
                mAdapterCallback.onDelete(modelLaundry)
            }
        }
    }

    interface JadwalAdapterCallback {
        fun onDelete(modelDatabase: ModelDatabaseJadwal?)
    }
}
