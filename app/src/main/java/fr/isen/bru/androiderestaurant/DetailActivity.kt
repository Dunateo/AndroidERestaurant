package fr.isen.bru.androiderestaurant

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso
import fr.isen.bru.androiderestaurant.adapter.ViewPagerAdapter
import fr.isen.bru.androiderestaurant.domain.FoodData


class DetailActivity : AppCompatActivity() {
    var foodDescription: TextView? = null
    var RecipeName: TextView? = null
    var RecipePrice: TextView? = null
    var foodImage: ViewPager? = null
    var key: String? = ""
    var imageUrl: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        RecipeName = findViewById<View>(R.id.txtRecipeName) as TextView
        RecipePrice = findViewById<View>(R.id.txtPrice) as TextView
        foodDescription = findViewById<View>(R.id.txtDescription) as TextView
        foodImage = findViewById<View>(R.id.ivImage2) as ViewPager
        val mBundle = intent.extras
        if (mBundle != null) {
             mBundle.getSerializable("Desc").let { serializedItem ->
                 val data = serializedItem as FoodData

                 val ingre = data.ingredients
                 val pr :StringBuilder = StringBuilder("")
                 for (content in ingre){
                        pr.append("- "+content.name_fr)
                     pr.append("\n")
                 }
                 foodDescription!!.text =pr.toString()

                 val phot = data.images

                 // Initializing the ViewPagerAdapter
                 val mViewPagerAdapter = ViewPagerAdapter(applicationContext, phot)
                 foodImage!!.adapter = mViewPagerAdapter
             }


            key = mBundle.getString("keyValue")
            imageUrl = mBundle.getString("Image")
            RecipeName!!.text = mBundle.getString("RecipeName")
            RecipePrice!!.text = mBundle.getString("price")




        }
    }

    fun createImage(imageUrl :String, foodImage : ImageView){
        val picasso = Picasso.get()
        if(imageUrl.isEmpty()){
            picasso.load(R.drawable.crocodile).into(foodImage)
        }else{
            picasso.load(imageUrl).into(foodImage)
        }

    }


}