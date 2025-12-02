package knu.mus.cryptocurrency_exchange_rate_viewer.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import knu.mus.cryptocurrency_exchange_rate_viewer.data.ExchangeRatesDataSource

// TODO move to domain
data class ExchangeRate(
    val shortName: String,
    val fullName: String,
    val imageUrl: String,
    var price: Float,
    var high24Hour: Float,
    var low24Hour: Float,
)

@HiltViewModel
class ExchangeRatesViewModel @Inject constructor(

) : ViewModel() {
    // TODO cache in db
    private val dataSource: ExchangeRatesDataSource = ExchangeRatesDataSource()

    private val _exchangeRates = MutableLiveData<List<ExchangeRate>>(listOf())
    val exchangeRates: LiveData<List<ExchangeRate>>
        get() = _exchangeRates

    fun fetchRates() {
        dataSource.getExchangeRates() {
            Log.d(TAG, "getExchangeRates -> ${it?.data?.size}")

            _exchangeRates.value = it?.data?.mapNotNull{ coin ->
                if (coin.rawExchangeRates == null)
                    null
                else ExchangeRate(
                    shortName = coin.info.name, 
                    fullName = coin.info.fullName,
                    imageUrl = coin.info.imageUrl,
                    price = coin.rawExchangeRates.price,
                    high24Hour = coin.rawExchangeRates.high24Hour,
                    low24Hour = coin.rawExchangeRates.low24Hour,
                )
            } ?: listOf()
        }
    }

    companion object {
        const val TAG = "EXRTViewModel";
    }
}

