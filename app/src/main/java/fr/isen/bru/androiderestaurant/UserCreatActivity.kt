package fr.isen.bru.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class UserCreatActivity : AppCompatActivity(){

    var name : EditText? = null
    var lastname: EditText? = null
    var street : EditText?= null
    var password: EditText? = null
    var email : EditText?= null
    var signup : Button? = null

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
}