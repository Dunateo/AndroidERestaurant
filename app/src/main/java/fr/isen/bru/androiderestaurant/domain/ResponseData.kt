package fr.isen.bru.androiderestaurant.domain

import java.io.Serializable

data class ResponseData (
    var items: List<FoodData>,
    val name_fr: String,
    val name_en: String
) : Serializable
