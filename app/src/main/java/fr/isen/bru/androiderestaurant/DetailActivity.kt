package fr.isen.bru.androiderestaurant

import android.content.Context
import android.content.Intent
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
import com.google.gson.JsonArray
import fr.isen.bru.androiderestaurant.adapter.ViewPagerAdapter
import fr.isen.bru.androiderestaurant.domain.FoodData
import fr.isen.bru.androiderestaurant.domain.OrderData
import java.io.FileNotFoundException


class DetailActivity : AppCompatActivity() {
    var foodDescription: TextView? = null
    var RecipeName: TextView? = null
    var RecipePrice: TextView? = null
    var foodImage: ViewPager? = null
    var key: String? = ""
    var imageUrl: String? = ""
    var quantity:Int = 1
    var quantityText : TextView? = null
    lateinit var order : OrderData
    var fileName: String = "Order.json"

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.menu_item -> {
                startActivity(
                    Intent(applicationContext, CartActivity::class.java))
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

    private fun orderSave(order : OrderData){
        var actual :MutableList<OrderData>
        val shared = this.getSharedPreferences(getString(R.string.fileName), Context.MODE_PRIVATE)
        val edit = shared.edit()

        try {
            applicationContext.openFileInput(fileName).use { inputStream ->
                inputStream.bufferedReader().use {
                    actual = Gson().fromJson( it.readText(), Array<OrderData>::class.java).toMutableList()

                    this.order.idOrder += 1
                    order.idOrder += 1

                    actual.add(order)
                    val nb = countIteminCart(actual)
                    countPastille(nb)
                    edit.putInt("qt", nb)

                    val newOne = Gson().toJson(actual)
                    applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
                        outputStream.write(newOne.toString().toByteArray())

                    }
                }
            }


        } catch(e: FileNotFoundException) {
            val orders = JsonArray()
            val parsed = Gson().toJsonTree(order)
            orders.add(Gson().toJsonTree(parsed))

            applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write(orders.toString().toByteArray())
            }
            edit.putInt("qt", 0)

        }
        edit.apply()


    }

    private fun countPastille(num :Int){
        val countText = findViewById<TextView>(R.id.menu_numb)
        countText.text = num.toString()
    }

    private  fun countIteminCart(list : MutableList<OrderData>) : Int{
        var count:Int = 0
        for (content in list){
            count += content.quantity
        }
        return count
    }
}