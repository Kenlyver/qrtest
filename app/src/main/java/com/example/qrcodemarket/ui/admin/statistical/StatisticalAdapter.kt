package com.example.qrcodemarket.ui.admin.statistical

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.qrcodemarket.R
import com.example.qrcodemarket.data.model_new.Statistical
import com.example.qrcodemarket.databinding.RecyclerStatisticalBinding

class StatisticalAdapter(
    private val access: List<Statistical>
) : RecyclerView.Adapter<StatisticalAdapter.StatisticalViewHolder>() {

    override fun getItemCount(): Int = access.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StatisticalViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recycler_statistical,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: StatisticalViewHolder, position: Int) {
        holder.recyclerStatisticalBinding.recyclerStatistical = access[position]
    }

    inner class StatisticalViewHolder(
        val recyclerStatisticalBinding: RecyclerStatisticalBinding
    ) : RecyclerView.ViewHolder(recyclerStatisticalBinding.root)

}