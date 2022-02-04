package fr.isen.rey.androiderestaurant.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.rey.androiderestaurant.R
import fr.isen.rey.androiderestaurant.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = RegisterFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit()
    }
}