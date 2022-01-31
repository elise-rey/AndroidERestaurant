package fr.isen.rey.androiderestaurant.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import fr.isen.rey.androiderestaurant.ListActivity
import fr.isen.rey.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.rey.androiderestaurant.network.Dish

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    var currentDish: Dish? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentDish = intent.getSerializableExtra(ListActivity.SELECTED_ITEM) as? Dish
        setupContent()
        incrementNbItem()
    }

    private fun setupContent() {
        binding.dishTitle.text = currentDish?.name
        binding.ingredients.text = currentDish?.ingredients?.joinToString(", ") { it.name }

        binding.viewPager.adapter = currentDish?.let { PhotoAdapter(this, it.images) }
    }

    private fun incrementNbItem() {
        var count: Int = 1
        binding.nbItem.text = count.toString()
        binding.lessButton.setOnClickListener {
            if (count == 0) {
                binding.nbItem.text = count.toString()
            } else {
                count--
                binding.nbItem.text = count.toString()
            }
        }
        binding.moreButton.setOnClickListener {
            count++
            binding.nbItem.text = count.toString()
        }
    }
}