package fr.isen.bru.androiderestaurant

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import fr.isen.bru.androiderestaurant.adapter.CartAdapter
import fr.isen.bru.androiderestaurant.adapter.FoodAdaptater
import fr.isen.bru.androiderestaurant.domain.OrderData
import fr.isen.bru.androiderestaurant.domain.ResponseData
import java.io.FileNotFoundException

class CartActivity :AppCompatActivity() {

    var cart : MutableList<OrderData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        read_file()
    }

     fun floattingButton(view: View?){
        Toast.makeText(applicationContext, "truc", Toast.LENGTH_LONG).show()
    }

    private fun read_file(){
        try {
            val parser = Gson()
            applicationContext.openFileInput("Order.json").use {inputStream ->
                inputStream.bufferedReader().use {
                    cart = parser.fromJson(it.readText(), Array<OrderData>::class.java).toMutableList()
                }
        }
            val foodRecycler = findViewById<RecyclerView>(R.id.RecyclerViewCart)
            foodRecycler.adapter = CartAdapter(cart!!, applicationContext)
            foodRecycler.layoutManager = LinearLayoutManager(this)
            foodRecycler.isVisible = true


        }catch (e : FileNotFoundException){
                Toast.makeText(applicationContext,"No cart found", Toast.LENGTH_LONG).show()
                val title = findViewById<TextView>(R.id.titleCart)
                title.text = "No cart found"
        }
    }
}