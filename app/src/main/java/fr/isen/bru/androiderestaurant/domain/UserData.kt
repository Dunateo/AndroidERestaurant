package fr.isen.bru.androiderestaurant.domain

import org.json.JSONObject
import java.io.Serializable

data class UserData(
    val firstName: String,
    val lastName: String,
    val email: String,
    val address: String,
    val password: String
):Serializable {
    fun createParam(parameters: JSONObject): JSONObject {
        parameters.put("firstname", firstName)
        parameters.put("lastname", lastName)
        parameters.put("address", address)
        parameters.put("email", email)
        parameters.put("password", password)
        return parameters
    }

    fun SignInParams(parameters: JSONObject): JSONObject {
        parameters.put("email", email)
        parameters.put("password", password)
        return parameters
    }
}