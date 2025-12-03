package knu.mus.cryptocurrency_exchange_rate_viewer.domain

import knu.mus.cryptocurrency_exchange_rate_viewer.data.db.CoinEntity

data class CoinItem(
    val shortName: String,
    val fullName: String,
    val imageUrl: String,
    var price: Float,
    var priceHigh24Hour: Float,
    var priceLow24Hour: Float,
    var lastUpdate: Long,
) {
    fun toCoinEntity() : CoinEntity {
        return CoinEntity(
            shortName = this.shortName,
            fullName = this.fullName,
            imageUrl = this.imageUrl,
            price = this.price,
            priceHigh24Hour = this.priceHigh24Hour,
            priceLow24Hour = this.priceLow24Hour,
            lastUpdate = this.lastUpdate,
        )
    }
}
