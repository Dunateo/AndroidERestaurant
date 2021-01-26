package fr.isen.bru.androiderestaurant.domain

import java.io.Serializable
import java.util.*

data class PriceData(
    val id: Long,
    val id_pizza: Long,
    val id_size: Long,
    val create_date: Date,
    val update_date: Date,
    val price: Double,
    val size: String
):Serializable