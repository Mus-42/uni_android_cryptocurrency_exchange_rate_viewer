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
import androidx.hilt.work.HiltWorkerFactory
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.InstallIn
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import knu.mus.cryptocurrency_exchange_rate_viewer.data.FetchExchangeRatesWorker

@HiltAndroidApp
class CryptocurrencyExchangeRateViewerApplication : Application(), Configuration.Provider {
    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface HiltWorkerFactoryEntryPoint {
        fun workerFactory(): HiltWorkerFactory
    }

    override val workManagerConfiguration =
        Configuration.Builder()
            .setWorkerFactory(EntryPoints.get(this, HiltWorkerFactoryEntryPoint::class.java).workerFactory())
            .build()

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

