package fr.isen.rey.androiderestaurant.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.rey.androiderestaurant.databinding.ActivityCartBinding
import fr.isen.rey.androiderestaurant.user.UserActivity

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadList()

        binding.orderButton.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
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
}