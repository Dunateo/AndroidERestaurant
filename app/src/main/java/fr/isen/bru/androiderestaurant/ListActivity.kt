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
import fr.isen.bru.androiderestaurant.domain.ResponseData
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


        volleyGet(foodRecycler)


    }

    private fun volleyGet( foodRecycler :RecyclerView) {

        val jsonResponses: MutableList<String> = ArrayList()
        val parameter = JSONObject()
        parameter.put("id_shop", "1")
        val requestQueue = Volley.newRequestQueue(this)
        val parser = GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()

        val jsonObjectRequest =
            JsonObjectRequest(Request.Method.POST, API_URL, parameter, Response.Listener<JSONObject?>() {
                fun onResponse(response: JSONObject) {
                    try {

                        val result :Array<ResponseData> = parser.fromJson(response["data"].toString(), Array<ResponseData>::class.java)
                        val adapter =FoodAdaptater(result[0].data, applicationContext)
                        foodRecycler.adapter = adapter
                        foodRecycler.layoutManager = LinearLayoutManager(this)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            }, Response.ErrorListener { error -> error.printStackTrace() })

        requestQueue.add(jsonObjectRequest)
    }


}

