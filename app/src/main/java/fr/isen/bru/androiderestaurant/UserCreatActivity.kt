package fr.isen.bru.androiderestaurant

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import fr.isen.bru.androiderestaurant.domain.RegisterData
import fr.isen.bru.androiderestaurant.domain.UserData
import kotlinx.android.synthetic.main.creation_layout.view.*
import org.json.JSONObject

class UserCreatActivity : AppCompatActivity(){

    var name : EditText? = null
    var lastname: EditText? = null
    var street : EditText?= null
    var password: EditText? = null
    var email : EditText?= null
    var signup : Button? = null
    var fileName: String = "Order.json"
    val url : String = "http://test.api.catering.bluecodegames.com/user/register"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.creation_layout)

        name =findViewById<EditText>(R.id.name)
        lastname = findViewById<EditText>(R.id.lastname)
        street = findViewById<EditText>(R.id.street)
        email = findViewById<EditText>(R.id.email)
        password = findViewById<EditText>(R.id.pass)
        signup = findViewById(R.id.submitCreate)


    }

    fun alreadyHaveAnAccount(view : View?){
        startActivity(Intent(applicationContext, SignInActivity::class.java))
    }

    fun register(view: View?){
        val user : UserData? = structureUserData()
        if (user != null){
            val queue = Volley.newRequestQueue(this)
            val req = signUp(user)
            queue.add(req)
        }else{
                Toast.makeText(applicationContext, "Some of the form is null",Toast.LENGTH_SHORT).show()
        }

    }

    private fun structureUserData():UserData?{
        if (name!!.text.isNotEmpty() && lastname!!.text.isNotEmpty() && street!!.text.isNotEmpty() && email!!.text.isNotEmpty()
            && password!!.text.isNotEmpty()){
            return UserData(name!!.text.toString(), lastname!!.text.toString(), email!!.text.toString(), street!!.text.toString(), password!!.text.toString())
        }
        return null
    }

    private fun signUp(user: UserData): JsonObjectRequest {

        val params = JSONObject()
        params.put("id_shop", "1")
        user.createParam(params)

        return JsonObjectRequest(
            Request.Method.POST, url, params,
            Response.Listener { response ->
                Gson().fromJson(response["data"].toString(), RegisterData::class.java).let {

                    val pref = this.getSharedPreferences(getString(R.string.fileName), Context.MODE_PRIVATE)
                    with(pref.edit()) {
                        putInt("id", it.id)
                        apply() }

                    Toast.makeText(applicationContext, "register !", Toast.LENGTH_SHORT).show()

                }
            },
            Response.ErrorListener {
                System.err.println(it)

                    Toast.makeText(applicationContext, "failed to register! check the form", Toast.LENGTH_SHORT).show()


            }
        )
    }


}