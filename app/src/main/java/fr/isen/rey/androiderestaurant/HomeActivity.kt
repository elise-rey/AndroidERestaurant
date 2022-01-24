package fr.isen.rey.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.widget.Toast
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
        binding.starter.setOnClickListener {
            //Toast.makeText(applicationContext, R.string.toastStarter, Toast.LENGTH_SHORT).show()startActivity(Intent(this, ListActivity::class.java))
            showList(LunchType.STARTER)
        }
        binding.main.setOnClickListener {
            //Toast.makeText(applicationContext, R.string.toastMain, Toast.LENGTH_SHORT).show()
            showList(LunchType.COURSE)
        }
        binding.dessert.setOnClickListener {
            //Toast.makeText(applicationContext, R.string.toastDessert, Toast.LENGTH_SHORT).show()
            showList(LunchType.DESSERT)
        }
    }

    private fun showList(item: LunchType) {
        val intent = Intent(this, ListActivity::class.java)
        intent.putExtra(CategoryType, item)
        startActivity(intent)
    }

    companion object {
        const val CategoryType = "CategoryType"
    }

}