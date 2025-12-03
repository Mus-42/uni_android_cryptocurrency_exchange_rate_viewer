package knu.mus.cryptocurrency_exchange_rate_viewer.data

import javax.inject.Inject
import dagger.hilt.android.qualifiers.ApplicationContext
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

import knu.mus.cryptocurrency_exchange_rate_viewer.domain.CoinItem
import knu.mus.cryptocurrency_exchange_rate_viewer.domain.Repository
import knu.mus.cryptocurrency_exchange_rate_viewer.data.db.CoinItemsDatabase
import knu.mus.cryptocurrency_exchange_rate_viewer.data.db.CoinItemDao

class RepositoryDataBase @Inject constructor(
    @ApplicationContext context: Context
): Repository {
    private val dao = CoinItemsDatabase.getDatabase(context).coinItemDao()

    override val itemsLiveData: LiveData<List<CoinItem>>
        get() {
            val entities = dao.getEntities()
            val mediator = MediatorLiveData<List<CoinItem>>()

            mediator.addSource(entities) { entities ->
                mediator.value = entities.map{it.toCoinItem()}
            }

            return mediator
        }

    override suspend fun addItem(item: CoinItem) {
        dao.addItem(item.toCoinEntity())
    }

    override suspend fun addItems(items: List<CoinItem>) {
        dao.addItems(items.map{ it.toCoinEntity()})
    }

    override suspend fun removeItem(item: CoinItem) {
        dao.removeItem(item.toCoinEntity())
    }

    override suspend fun changeItem(item: CoinItem) {
        dao.changeItem(item.toCoinEntity())
    }

    companion object {
        const val TAG = "RepoDB";
    }
}

