package fr.isen.bru.androiderestaurant

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.bru.androiderestaurant.domain.RegisterData
import fr.isen.bru.androiderestaurant.domain.UserData
import org.json.JSONObject

class SignInActivity : AppCompatActivity(){

    var password: EditText? = null
    var email: EditText? = null
    val url : String = "http://test.api.catering.bluecodegames.com/user/login"

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_signin)

        email = findViewById<EditText>(R.id.emailIn)
        password = findViewById<EditText>(R.id.passIn)
    }


    fun logIn(view : View?){
        val user : UserData? = structureUserData()

        if (user != null){
            val queue = Volley.newRequestQueue(this)
            val req = signIn(user)
            queue.add(req)
        }else{
            Toast.makeText(applicationContext, "Not the good mail or password",Toast.LENGTH_SHORT).show()
        }
    }



    private fun structureUserData():UserData?{
        if ( email!!.text.isNotEmpty() && password!!.text.isNotEmpty()){
            return UserData("", "", email!!.text.toString(), "", password!!.text.toString())
        }
        return null
    }

    private fun signIn(user: UserData): JsonObjectRequest {

        val parameter = JSONObject()
        parameter.put("id_shop", "1")
        user.SignInParams(parameter)
        return JsonObjectRequest(
            Request.Method.POST, url, parameter,
            Response.Listener { response ->

                Gson().fromJson(response["data"].toString(),RegisterData::class.java).let {

                    val pref = this.getSharedPreferences(getString(R.string.fileName), Context.MODE_PRIVATE)
                    with(pref.edit()) {
                        putInt("id", it.id)
                        apply() }

                    Toast.makeText(applicationContext, "Welcome back", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                System.err.println(error)
            })
    }

}