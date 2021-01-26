package fr.isen.bru.androiderestaurant

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.bru.androiderestaurant.adapter.FoodAdaptater
import fr.isen.bru.androiderestaurant.domain.FoodData
import org.json.JSONException
import org.json.JSONObject


class ListActivity : AppCompatActivity(){

    private var title:TextView? = null
    private val API_URL = "http://test.api.catering.bluecodegames.com/menu"

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

        volleyGet()


    }

    private fun volleyGet() {

        val jsonResponses: MutableList<String> = ArrayList()
        val parameter = JSONObject()
        parameter.put("id_shop", "1")
        val requestQueue = Volley.newRequestQueue(this)
        val parser = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()

        val jsonObjectRequest =
            JsonObjectRequest(Request.Method.POST, API_URL, parameter, Response.Listener<JSONObject?>() {
                fun onResponse(response: JSONObject) {
                    try {
                        val jsonArray = response.getJSONArray("data")
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            val email = jsonObject.getString("email")
                            jsonResponses.add(email)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }, Response.ErrorListener { error -> error.printStackTrace() })
        requestQueue.add(jsonObjectRequest)
    }


}

