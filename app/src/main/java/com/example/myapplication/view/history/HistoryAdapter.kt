package com.example.myapplication.view.history

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.ModelDatabase
import com.example.myapplication.utils.BitmapManager.base64ToBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.myapplication.R
import com.example.myapplication.databinding.ListHistoryAbsenBinding

class HistoryAdapter(
    private val mContext: Context,
    private val modelDatabase: MutableList<ModelDatabase>,
    private val mAdapterCallback: HistoryAdapterCallback
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    fun setDataAdapter(items: List<ModelDatabase>) {
        modelDatabase.clear()
        modelDatabase.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListHistoryAbsenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = modelDatabase[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return modelDatabase.size
    }

    inner class ViewHolder(private val binding: ListHistoryAbsenBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ModelDatabase) {
            binding.tvNomor.text = data.uid.toString()
            binding.tvNama.text = data.nama
            binding.tvLokasi.text = data.lokasi
            binding.tvAbsenTime.text = data.tanggal
            binding.tvStatusAbsen.text = data.keterangan

            Glide.with(mContext)
                .load(base64ToBitmap(data.fotoSelfie))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_photo_camera)
                .into(binding.imageProfile)

            when (data.keterangan) {
                "Absen Masuk" -> {
                    binding.colorStatus.setBackgroundResource(R.drawable.bg_circle_radius)
                    binding.colorStatus.backgroundTintList = ColorStateList.valueOf(Color.GREEN)
                }
            }

            binding.cvHistory.setOnClickListener {
                val modelLaundry = modelDatabase[adapterPosition]
                mAdapterCallback.onDelete(modelLaundry)
            }
        }
    }

    interface HistoryAdapterCallback {
        fun onDelete(modelDatabase: ModelDatabase)
    }
}
