package fr.isen.bru.androiderestaurant

class FoodData {
    var itemName: String? = null
        private set
    var itemDescription: String? = null
        private set
    var itemPrice: String? = null
        private set
    var itemImage: String? = null
        private set
    var key: String? = null

    constructor() {}
    constructor(
        itemName: String?,
        itemDescription: String?,
        itemPrice: String?,
        itemImage: String?
    ) {
        this.itemName = itemName
        this.itemDescription = itemDescription
        this.itemPrice = itemPrice
        this.itemImage = itemImage
    }

}