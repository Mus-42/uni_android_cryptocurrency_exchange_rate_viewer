package knu.mus.cryptocurrency_exchange_rate_viewer.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import knu.mus.cryptocurrency_exchange_rate_viewer.data.ExchangeRatesDataSource
import knu.mus.cryptocurrency_exchange_rate_viewer.domain.CoinItem
import knu.mus.cryptocurrency_exchange_rate_viewer.domain.Repository

@HiltViewModel
class ExchangeRatesViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {
    // TODO cache in db
    private val dataSource: ExchangeRatesDataSource = ExchangeRatesDataSource()

    private val _exchangeRates = MutableLiveData<List<CoinItem>>(listOf())
    val exchangeRates: LiveData<List<CoinItem>>
        get() = _exchangeRates

    fun fetchRates() {
        dataSource.getExchangeRates() {
            Log.d(TAG, "getExchangeRates -> ${it?.data?.size}")

            _exchangeRates.value = it?.data?.mapNotNull{ rawCoin -> rawCoin.toCoinItem() } ?: listOf()
        }
    }

    companion object {
        const val TAG = "EXRTViewModel";
    }
}

