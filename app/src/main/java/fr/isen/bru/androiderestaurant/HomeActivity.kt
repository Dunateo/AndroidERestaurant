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

    fun mano(): List<FoodData>{
        var truc :ArrayList<FoodData> = ArrayList()
        val elem = FoodData()
        truc.add(elem);
        truc.add(elem);
        truc.add(elem);
        return truc;
    }
    fun btnEntree(view: View?) {
       val toat = Toast.makeText(this.applicationContext, "Entrées", Toast.LENGTH_SHORT)
        toat.show()
    }
    fun btnRecipe(view: View?) {
        startActivity(
            Intent(applicationContext, DetailActivity::class.java)
                .putExtra("recipeNameKey", myFoodList.toString())

        )
    }


}
