package knu.mus.cryptocurrency_exchange_rate_viewer.data

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExchangeRatesDataSource {
    private val BASE_URL = "https://min-api.cryptocompare.com"

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var exchangeRatesService = retrofit.create(ExchangeRatesService::class.java)

    fun getExchangeRates(resultCallback: (ExchangeRatesList?) -> Unit){
        val call = exchangeRatesService.getExchangeRatesList()

        // TODO handle errors in some other way
        call?.enqueue(object: Callback<ExchangeRatesList?>{
            override fun onResponse(call: Call<ExchangeRatesList?>, response: Response<ExchangeRatesList?>) {
                val exchangeRates = response.body()
                Log.d(TAG, "fetched data: ${exchangeRates?.data?.size}")
                resultCallback(exchangeRates)
            }

            override fun onFailure(call: Call<ExchangeRatesList?>, t: Throwable) {
                Log.d(TAG, "failed to fetch data")
                resultCallback(null)
            }
        })
    }


    companion object {
        const val TAG = "EXRTData"
    }
}
