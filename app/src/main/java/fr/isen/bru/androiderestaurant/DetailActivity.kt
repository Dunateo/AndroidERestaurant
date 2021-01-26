package fr.isen.bru.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    var foodDescription: TextView? = null
    var RecipeName: TextView? = null
    var RecipePrice: TextView? = null
    var foodImage: ImageView? = null
    var key: String? = ""
    var imageUrl: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        RecipeName = findViewById<View>(R.id.txtRecipeName) as TextView
        RecipePrice = findViewById<View>(R.id.txtPrice) as TextView
        foodDescription = findViewById<View>(R.id.txtDescription) as TextView
        foodImage = findViewById<View>(R.id.ivImage2) as ImageView
        val mBundle = intent.extras
        if (mBundle != null) {
            foodDescription!!.text = mBundle.getString("Description")
            key = mBundle.getString("keyValue")
            imageUrl = mBundle.getString("Image")
            RecipeName!!.text = mBundle.getString("RecipeName")
            RecipePrice!!.text = mBundle.getString("price")

            val picasso = Picasso.get()
            if(imageUrl.isNullOrEmpty()){
                picasso.load(R.drawable.crocodile).into(foodImage)
            }else{
                picasso.load(imageUrl).into(foodImage)
            }

        }
    }


}