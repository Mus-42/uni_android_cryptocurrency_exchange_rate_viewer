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
    val rawExchangeRates: RawExchangeRates?,
) {
    fun toCoinItem() : CoinItem? {
        if (this.rawExchangeRates == null)
            return null;

        return CoinItem(
            shortName = this.info.name,
            fullName = this.info.fullName,
            imageUrl = this.info.imageUrl,
            price = this.rawExchangeRates.price,
            priceHigh24Hour = this.rawExchangeRates.high24Hour,
            priceLow24Hour = this.rawExchangeRates.low24Hour,
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

data class RawExchangeRates(
    // TODO add other if needed
    @SerializedName("PRICE")
    var price: Float,
    @SerializedName("HIGH24HOUR")
    var high24Hour: Float,
    @SerializedName("LOW24HOUR")
    var low24Hour: Float,
)

interface ExchangeRatesService {
    @GET("data/top/totalvolfull?")
    fun getExchangeRatesList(
        @Query("limit") limit: Int = 30,
        @Query("tsym") tsym: String = "USD",
    ): Call<ExchangeRatesList>
}
