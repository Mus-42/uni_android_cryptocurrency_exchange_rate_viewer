package knu.mus.cryptocurrency_exchange_rate_viewer.data

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExchangeRatesDataSource {
    private val BASE_URL = "https://min-api.cryptocompare.com"

    init {
        Log.d(TAG, "created data source")
    }

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var exchangeRatesService = retrofit.create(ExchangeRatesService::class.java)

    suspend fun getExchangeRates() : ExchangeRatesList? {
        try {
            val exchangeRates = exchangeRatesService.getExchangeRatesList()
            Log.d(TAG, "fetched data: ${exchangeRates?.data?.size}")
            return exchangeRates;
        } catch (e: Exception) {
            Log.d(TAG, "failed to fetch data: ${e}")
            return null
        }
    }


    companion object {
        const val TAG = "EXRTData"
    }
}
