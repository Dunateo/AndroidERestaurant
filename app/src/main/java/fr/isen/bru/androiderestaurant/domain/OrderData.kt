package fr.isen.bru.androiderestaurant.domain

import java.io.Serializable

data class OrderData(
    val item : FoodData,
    val idOrder : Int,
    var quantity : Int,
    var price : Double


):Serializable