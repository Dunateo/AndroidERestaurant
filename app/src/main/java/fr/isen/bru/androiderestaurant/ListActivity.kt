package fr.isen.bru.androiderestaurant

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.bru.androiderestaurant.adapter.FoodAdaptater
import fr.isen.bru.androiderestaurant.domain.FoodData

class ListActivity : AppCompatActivity(){

    private var title:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val bundle = intent.extras
        title = findViewById<View>(R.id.title) as TextView

        if (bundle != null) {
            title!!.text = bundle.getString("title")
        }

        val foodRecycler = findViewById<RecyclerView>(R.id.RecyclerView)
        val data = listOf(FoodData("crocodile","petite douceur","30",null),
        FoodData("kangouroo","les iles sont pour vous", "20", null)
        )

        val adapter =FoodAdaptater(data, applicationContext)
        foodRecycler.adapter = adapter
        foodRecycler.layoutManager = LinearLayoutManager(this)


    }


}

