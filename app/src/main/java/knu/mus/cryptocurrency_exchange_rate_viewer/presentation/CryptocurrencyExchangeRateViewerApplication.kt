package knu.mus.cryptocurrency_exchange_rate_viewer.presentation

import javax.inject.Inject
import java.util.concurrent.TimeUnit
import java.util.concurrent.Executors
import android.app.Application
import androidx.work.PeriodicWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Configuration
import androidx.work.WorkRequest
import dagger.hilt.android.HiltAndroidApp
import knu.mus.cryptocurrency_exchange_rate_viewer.data.FetchExchangeRatesWorker

@HiltAndroidApp
class CryptocurrencyExchangeRateViewerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Refresh every 5 minutes
        val workRequest = PeriodicWorkRequest.Builder(
            FetchExchangeRatesWorker::class.java, 
            5, TimeUnit.MINUTES,
        ).build()

        WorkManager.getInstance(applicationContext)
            .enqueue(workRequest)
    }
}

