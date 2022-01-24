package fr.isen.rey.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.rey.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listenClick()
    }
    private fun listenClick() {
        binding.starters.setOnClickListener() {
            Toast.makeText(applicationContext, "Ouverture des entrées...", Toast.LENGTH_SHORT).show()
        }
        binding.courses.setOnClickListener() {
            Toast.makeText(applicationContext, "Ouverture des plats...", Toast.LENGTH_SHORT).show()
        }
        binding.desserts.setOnClickListener() {
            Toast.makeText(applicationContext, "Ouverture des desserts...", Toast.LENGTH_SHORT).show()
        }
    }
}