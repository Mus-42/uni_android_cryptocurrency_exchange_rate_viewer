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
    @Assisted params: WorkerParameters,
    private val repository: Repository,
) : CoroutineWorker(context, params) {
    private val dataSource: ExchangeRatesDataSource = ExchangeRatesDataSource()

    override suspend fun doWork(): Result {
        val exchangeRates = dataSource.getExchangeRates() ?: return Result.failure();
        Log.d(TAG, "getExchangeRates -> ${exchangeRates.data.size}")
        val coinItems = exchangeRates.data.mapNotNull{ it.toCoinItem() };
        repository.addItems(coinItems);
        return Result.success()
    }

    companion object {
        const val TAG = "FetchWorker"
    }
}
