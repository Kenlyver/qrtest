package com.example.qrcodemarket.ui.admin.market

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.qrcodemarket.R
import com.example.qrcodemarket.data.model_new.Market
import com.example.qrcodemarket.databinding.RecycleMarketItemBinding


class MarketAdapter(
    private val markets:List<Market>
):RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {

    override fun getItemCount(): Int = markets.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MarketViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recycle_market_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MarketAdapter.MarketViewHolder, position: Int) {
        holder.recycleMarketItemBinding.marketbinding = markets[position]
    }

    inner class MarketViewHolder(
        val recycleMarketItemBinding: RecycleMarketItemBinding
    ) : RecyclerView.ViewHolder(recycleMarketItemBinding.root)
}