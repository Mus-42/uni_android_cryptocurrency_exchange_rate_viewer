package knu.mus.cryptocurrency_exchange_rate_viewer.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.google.gson.annotations.SerializedName
import knu.mus.cryptocurrency_exchange_rate_viewer.domain.CoinItem

data class ExchangeRatesList(
    @SerializedName("Data")
    val data: List<Coin>
)

data class Coin(
    @SerializedName("CoinInfo")
    val info: CoinInfo,
    @SerializedName("RAW")
    val rawExchangeRates: RawExchangeRatesWrapper?,
) {
    fun toCoinItem() : CoinItem? {
        return CoinItem(
            shortName = this.info.name,
            fullName = this.info.fullName,
            imageUrl = this.info.imageUrl,
            price = this.rawExchangeRates?.usd?.price ?: return null,
            priceHigh24Hour = this.rawExchangeRates?.usd?.high24Hour ?: return null,
            priceLow24Hour = this.rawExchangeRates?.usd?.low24Hour ?: return null,
            lastUpdate = this.rawExchangeRates?.usd?.lastUpdate ?: return null,
        )
    }
}

data class CoinInfo(
    @SerializedName("Name")
    val name: String,
    @SerializedName("FullName")
    val fullName: String,
    @SerializedName("ImageUrl")
    val imageUrl: String,
    // TODO rest of the fields
)

data class RawExchangeRatesWrapper(
    @SerializedName("USD")
    var usd: RawExchangeRates?,
)

data class RawExchangeRates(
    // TODO add other if needed
    @SerializedName("PRICE")
    var price: Float,
    @SerializedName("HIGH24HOUR")
    var high24Hour: Float,
    @SerializedName("LOW24HOUR")
    var low24Hour: Float,
    @SerializedName("LASTUPDATE")
    var lastUpdate: Long,
)

interface ExchangeRatesService {
    @GET("data/top/totalvolfull?")
    suspend fun getExchangeRatesList(
        @Query("limit") limit: Int = 30,
        @Query("tsym") tsym: String = "USD",
    ): ExchangeRatesList
}
