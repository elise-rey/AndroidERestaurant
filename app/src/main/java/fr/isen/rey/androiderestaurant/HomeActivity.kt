package fr.isen.rey.androiderestaurant

import android.R
import android.app.ActivityOptions
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import fr.isen.rey.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listenClick()
        //showMap()
    }

    private fun listenClick() {
        binding.starter.setOnClickListener {
            showList(LunchType.STARTER)
        }
        binding.main.setOnClickListener {
            showList(LunchType.MAIN)
        }
        binding.dessert.setOnClickListener {
            showList(LunchType.DESSERT)
        }
    }

    private fun showList(item: LunchType) {
        val intent = Intent(this, ListActivity::class.java)
        intent.putExtra(CategoryType, item)
        startActivity(intent)
    }

    private fun showMap() {
        val urlIntent = Uri.parse("https://www.google.com/maps/place/ISEN+Yncr%C3%A9a+M%C3%A9diterran%C3%A9e+-+Campus+de+Toulon/@43.1206241,5.9374718,17z/data=!3m1!4b1!4m5!3m4!1s0x12c91b0a44cc26c9:0x30eab5841931dc29!8m2!3d43.1206202!4d5.9396605")
        val mapIntent = Intent(Intent.ACTION_VIEW, urlIntent)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    companion object {
        const val CategoryType = "CategoryType"
    }

}