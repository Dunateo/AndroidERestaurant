package fr.isen.bru.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.isen.bru.androiderestaurant.domain.FoodData
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity() {

    var myFoodList: List<FoodData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this.applicationContext, "Destroy main", Toast.LENGTH_SHORT).show()
    }


    fun btnMenu(view: View?) {
        if (R.id.entree == view?.id){
            Toast.makeText(this.applicationContext, "Entrées", Toast.LENGTH_SHORT).show()
            changePage("Entrées",0)
        }else if (R.id.plat == view?.id){
            Toast.makeText(this.applicationContext, "Plats", Toast.LENGTH_SHORT).show()
            changePage("Plat",1)
        }else if (R.id.dessert == view?.id){
            Toast.makeText(this.applicationContext, "Dessert", Toast.LENGTH_SHORT).show()
            changePage("Dessert",2)
        }
    }


    fun changePage(string: String, category :Int) {
        startActivity(
            Intent(applicationContext, ListActivity::class.java)
                .putExtra("title",string )
                .putExtra("cat", category)
        )
    }


}
