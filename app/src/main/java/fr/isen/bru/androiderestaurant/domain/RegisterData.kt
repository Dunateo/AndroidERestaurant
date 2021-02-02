package fr.isen.bru.androiderestaurant.domain

import java.io.Serializable

data class RegisterData(
    val id:Int,
    val code: String,
    val id_shop: Int
) : Serializable