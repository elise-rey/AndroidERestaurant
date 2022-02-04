package fr.isen.rey.androiderestaurant.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.rey.androiderestaurant.R
import fr.isen.rey.androiderestaurant.databinding.ActivityUserBinding

interface UserActivityFragmentInteraction {
    fun showLogin()
    fun showRegister()
}

class UserActivity : AppCompatActivity(), UserActivityFragmentInteraction {
    lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = RegisterFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, fragment).commit()
    }

    override fun showLogin() {
        val loginFragment = LoginFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, loginFragment).commit()
    }

    override fun showRegister() {
        val registerFragment = RegisterFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, registerFragment).commit()
    }
}