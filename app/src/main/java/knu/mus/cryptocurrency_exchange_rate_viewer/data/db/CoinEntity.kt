package knu.mus.cryptocurrency_exchange_rate_viewer.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import knu.mus.cryptocurrency_exchange_rate_viewer.domain.CoinItem

@Entity(tableName = "coins_table")
data class CoinEntity(
    @PrimaryKey
    @ColumnInfo(name = "short_name")
    val shortName: String,
    @ColumnInfo(name = "full_name")
    val fullName: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "price")
    var price: Float,
    @ColumnInfo(name = "high_24")
    var priceHigh24Hour: Float,
    @ColumnInfo(name = "low_24")
    var priceLow24Hour: Float,
) {
    fun toCoinItem() : CoinItem {
        return CoinItem(
            shortName = this.shortName,
            fullName = this.fullName,
            imageUrl = this.imageUrl,
            price = this.price,
            priceHigh24Hour = this.priceHigh24Hour,
            priceLow24Hour = this.priceLow24Hour,
        )
    }
}
