package knu.mus.cryptocurrency_exchange_rate_viewer.presentation

import androidx.recyclerview.widget.DiffUtil
import knu.mus.cryptocurrency_exchange_rate_viewer.domain.CoinItem

class CoinDiffUtil: DiffUtil.ItemCallback<CoinItem>(){
    override fun areItemsTheSame(
        oldItem: CoinItem,
        newItem: CoinItem
    ): Boolean {
        return oldItem.shortName == newItem.shortName
    }

    override fun areContentsTheSame(
        oldItem: CoinItem,
        newItem: CoinItem
    ): Boolean {
       return oldItem == newItem
    }

}