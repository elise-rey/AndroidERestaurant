package fr.isen.rey.androiderestaurant.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.rey.androiderestaurant.databinding.ActivityEmptyCartBinding

class EmptyCartActivity : AppCompatActivity() {
    lateinit var binding: ActivityEmptyCartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmptyCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}