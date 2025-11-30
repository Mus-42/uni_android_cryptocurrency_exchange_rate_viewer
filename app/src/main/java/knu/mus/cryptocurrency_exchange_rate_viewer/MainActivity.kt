package knu.mus.cryptocurrency_exchange_rate_viewer

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import knu.mus.cryptocurrency_exchange_rate_viewer.data.ExchangeRatesDataSource
import knu.mus.cryptocurrency_exchange_rate_viewer.data.ExchangeRatesList
import knu.mus.cryptocurrency_exchange_rate_viewer.presentation.ExchangeRatesViewModel

class MainActivity : AppCompatActivity() {
    val exchangeRatesViewModel by viewModels<ExchangeRatesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // dummy example to check that it works
        exchangeRatesViewModel.fetchRates();
        exchangeRatesViewModel.exchangeRates.observe(this) {
            Log.d(TAG, "exchange rates list: ${it}")
        }
    }

    companion object {
        const val TAG = "MainActivity";
    }
}
