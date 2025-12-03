package knu.mus.cryptocurrency_exchange_rate_viewer.presentation

import kotlinx.coroutines.launch
import javax.inject.Inject
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import knu.mus.cryptocurrency_exchange_rate_viewer.data.ExchangeRatesDataSource
import knu.mus.cryptocurrency_exchange_rate_viewer.domain.CoinItem
import knu.mus.cryptocurrency_exchange_rate_viewer.domain.Repository

@HiltViewModel
class ExchangeRatesViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    private val dataSource: ExchangeRatesDataSource = ExchangeRatesDataSource()

    val exchangeRates: LiveData<List<CoinItem>>
        get() = repository.itemsLiveData

    fun refreshRates() {
        dataSource.getExchangeRates { rates ->
            Log.d(TAG, "getExchangeRates -> ${rates?.data?.size}")

            viewModelScope.launch {
                rates?.data?.mapNotNull{ it.toCoinItem() }?.forEach { repository.addItem(it) }
            }
        }
    }

    companion object {
        const val TAG = "EXRTViewModel"
    }
}

