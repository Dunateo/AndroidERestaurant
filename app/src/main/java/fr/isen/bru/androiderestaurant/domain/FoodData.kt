package fr.isen.bru.androiderestaurant.domain
import java.io.Serializable

data class FoodData(
    val prices: List<PriceData>,
    val ingredients: List<IngreData>,
    val name_fr: String,
    val name_en: String,
    val categ_name_fr: String,
    val categ_name_en: String,
    val id: Long,
    val id_category: Long,
    val images: List<String>

) :Serializable