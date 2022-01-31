package fr.isen.rey.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.rey.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.rey.androiderestaurant.network.Dish
import java.io.Serializable

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTitle()
    }

    private fun setupTitle() {
        val currentDish = intent.getSerializableExtra(ListActivity.SELECTED_ITEM) as? Dish
        binding.item.text = currentDish?.name
    }
}