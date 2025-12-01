package knu.mus.cryptocurrency_exchange_rate_viewer.domain

data class ListItem(
    val currency: String,
    val url: String,
    val exchange_rate: Double,
    val date: String,
)