package fr.isen.bru.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity() {

    var myFoodList: List<FoodData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        myFoodList = mano()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this.applicationContext, "Destroy main", Toast.LENGTH_SHORT).show()
    }

    fun mano(): List<FoodData>{
        var truc :ArrayList<FoodData> = ArrayList()
        val elem = FoodData()
        truc.add(elem);
        truc.add(elem);
        truc.add(elem);
        return truc;
    }

    fun btnMenu(view: View?) {
        if (R.id.entree == view?.id){
            Toast.makeText(this.applicationContext, "Entrées", Toast.LENGTH_SHORT).show()
            changePage("Entrées")
        }else if (R.id.plat == view?.id){
            Toast.makeText(this.applicationContext, "Plat", Toast.LENGTH_SHORT).show()
            changePage("Plat")
        }else if (R.id.dessert == view?.id){
            Toast.makeText(this.applicationContext, "Dessert", Toast.LENGTH_SHORT).show()
            changePage("Dessert")
        }
    }


    fun changePage(string: String) {
        startActivity(
            Intent(applicationContext, ListActivity::class.java)
                .putExtra("title",string )
        )
    }


}
