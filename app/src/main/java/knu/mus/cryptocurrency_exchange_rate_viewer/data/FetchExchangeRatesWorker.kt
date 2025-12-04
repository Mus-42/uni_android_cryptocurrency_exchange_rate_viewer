package knu.mus.cryptocurrency_exchange_rate_viewer.data

import javax.inject.Inject
import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.hilt.work.HiltWorker
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import knu.mus.cryptocurrency_exchange_rate_viewer.domain.Repository

@HiltWorker
class FetchExchangeRatesWorker @AssistedInject constructor(
    @Assisted context: Context, 
    @Assisted params: WorkerParameters
) : CoroutineWorker(context, params) {
    // This should be in a constructor but for some reason it fails deep inside WorkerFactory
    // TODO write custom worker factory and move that to constructor???
    @Inject
    lateinit var repository: Repository

    private val dataSource: ExchangeRatesDataSource = ExchangeRatesDataSource()

    override suspend fun doWork(): Result {
        val exchangeRates = dataSource.getExchangeRates() ?: return Result.failure();
        Log.d(TAG, "getExchangeRates -> ${exchangeRates.data.size}")
        exchangeRates.data.mapNotNull{ it.toCoinItem() }.let{ repository.addItems(it) }
        return Result.success()
    }

    companion object {
        const val TAG = "FetchWorker"
    }
}
