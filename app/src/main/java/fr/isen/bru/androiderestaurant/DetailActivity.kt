package fr.isen.bru.androiderestaurant

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.bru.androiderestaurant.adapter.ViewPagerAdapter
import fr.isen.bru.androiderestaurant.domain.FoodData
import fr.isen.bru.androiderestaurant.domain.OrderData


class DetailActivity : AppCompatActivity() {
    var foodDescription: TextView? = null
    var RecipeName: TextView? = null
    var RecipePrice: TextView? = null
    var foodImage: ViewPager? = null
    var key: String? = ""
    var imageUrl: String? = ""
    var quantity:Int = 0
    var quantityText : TextView? = null
    lateinit var order : OrderData

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            R.id.menu_item -> {
                Toast.makeText(this, "Tapped on icon", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        RecipeName = findViewById<View>(R.id.txtRecipeName) as TextView
        RecipePrice = findViewById<View>(R.id.txtPrice) as TextView
        foodDescription = findViewById<View>(R.id.txtDescription) as TextView
        foodImage = findViewById<View>(R.id.ivImage2) as ViewPager
        quantityText = findViewById<View>(R.id.quantityText) as TextView

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

                 order = OrderData(data,0,quantity,data.prices[0].price)
             }


            key = mBundle.getString("keyValue")
            imageUrl = mBundle.getString("Image")
            RecipeName!!.text = mBundle.getString("RecipeName")
            RecipePrice!!.text = mBundle.getString("price")




        }
    }

    fun quantityUp(view: View?){
        quantity += 1
        quantityText!!.text = quantity.toString()

        RecipePrice!!.text = priceBeau((order.price*quantity).toString())
        order.quantity = quantity

    }

    fun quantityDown(view: View?){
        if (quantity > 1){
            quantity -= 1
            RecipePrice!!.text = priceBeau((order.price*quantity).toString())

        }else{
            RecipePrice!!.text = priceBeau(order.price.toString())

        }
        order.quantity = quantity
        quantityText!!.text = quantity.toString()

    }

    private fun priceBeau(str : String) : String{
        val pr : StringBuilder = java.lang.StringBuilder()
        pr.append(str)
        pr.append(" Rs")
        return pr.toString()
    }
    fun btnOrder(view: View?){
        orderSave(this.order)

        val message :StringBuilder = java.lang.StringBuilder()
        message.append(order.quantity)
        message.append(" x ")
        message.append(order.item.name_fr)

        val snackbar: Snackbar = Snackbar
            .make(view!!, message.toString(), Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    fun orderSave(order : OrderData){
        val order = Gson().toJson(order)
        applicationContext.openFileOutput("Order.json", Context.MODE_PRIVATE).use {
            it.write(order.toByteArray())
        }
    }


}