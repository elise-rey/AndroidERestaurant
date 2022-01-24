package fr.isen.rey.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.rey.androiderestaurant.databinding.ActivityListBinding

enum class LunchType {
    STARTER, COURSE, DESSERT
}

class ListActivity : AppCompatActivity() {
    lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val item: LunchType = intent.getSerializableExtra(HomeActivity.CategoryType) as? LunchType ?: LunchType.STARTER
        binding.textView.setText("extra " + item)
    }
}