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
        // Toasts
        binding.starters.setOnClickListener {
            Toast.makeText(applicationContext, R.string.toastStarters, Toast.LENGTH_SHORT).show()
        }
        binding.courses.setOnClickListener {
            Toast.makeText(applicationContext, R.string.toastCourses, Toast.LENGTH_SHORT).show()
        }
        binding.desserts.setOnClickListener {
            Toast.makeText(applicationContext, R.string.toastDesserts, Toast.LENGTH_SHORT).show()
        }

        // Redirection

    }
}