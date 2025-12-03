package knu.mus.cryptocurrency_exchange_rate_viewer.domain

import androidx.lifecycle.LiveData

interface Repository {
    val itemsLiveData: LiveData<List<CoinItem>>

    suspend fun addItem(item: CoinItem)
    suspend fun removeItem(item: CoinItem)
    suspend fun changeItem(item: CoinItem)
}


