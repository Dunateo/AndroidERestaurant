package fr.isen.bru.androiderestaurant.domain
import java.io.Serializable

class FoodData :Serializable{
    var itemName: String? = null
        private set
    var itemDescription: String? = null
        private set
    var itemPrice: String? = null
        private set
    var itemImage: String? = null
        private set
    var key: String? = null

    var id_category: Long? = null
    private set

    constructor() {}
    constructor(
        itemName: String?,
        itemDescription: String?,
        itemPrice: String?,
        itemImage: String?,
        id_category: Long?
    ) {
        this.itemName = itemName
        this.itemDescription = itemDescription
        this.itemPrice = itemPrice
        this.itemImage = itemImage
        this.id_category = id_category
    }

}