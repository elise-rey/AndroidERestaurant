package fr.isen.rey.androiderestaurant.cart

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.rey.androiderestaurant.databinding.ActivityCartBinding
import fr.isen.rey.androiderestaurant.network.NetworkConstants
import fr.isen.rey.androiderestaurant.user.UserActivity
import org.json.JSONObject

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadList()

        binding.orderButton.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            getResult.launch(intent)
        }
    }

    private val getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "Commande envoyée", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadList() {
        val cart = Cart.getCart(this)
        val items = cart.items

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = CartAdapter(items) {
            cart.removeItem(it)
            cart.save(this)
            loadList()
        }
    }

    private fun makeRequest() {
        val url = NetworkConstants.BASE_URL + NetworkConstants.ORDER
        val queue = Volley.newRequestQueue(this)
        val cart = Cart.getCart(this)
        val sharedPreferences = getSharedPreferences(UserActivity.USER_PREFERENCES_NAME, Context.MODE_PRIVATE)

        val parameters = JSONObject()
        parameters.put(NetworkConstants.KEY_MSG, cart.toJson())
        parameters.put(NetworkConstants.KEY_USER, sharedPreferences.getInt(UserActivity.ID_USER, -1))
        parameters.put(NetworkConstants.KEY_SHOP, NetworkConstants.SHOP)

        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            parameters,
            {
                cart.clear()
                cart.save(this)
                finish()
            },
            {
                Log.d("request", it.message ?: "erreur")
            }
        )
        queue.add(request)
    }
}