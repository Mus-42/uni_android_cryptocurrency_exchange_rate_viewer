package knu.mus.cryptocurrency_exchange_rate_viewer

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import knu.mus.cryptocurrency_exchange_rate_viewer.data.ExchangeRatesDataSource
import knu.mus.cryptocurrency_exchange_rate_viewer.data.ExchangeRatesList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // dummy example to check that it works
        // val dataSource = ExchangeRatesDataSource()
        // dataSource.getExchangeRate({ list: ExchangeRatesList? ->
        //     Log.d(TAG, "exchange rates list: ${list}")
        // })
    }

    companion object {
        const val TAG = "MainActivity";
    }
}
