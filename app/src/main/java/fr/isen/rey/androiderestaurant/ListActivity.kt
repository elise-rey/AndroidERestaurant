package fr.isen.rey.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import fr.isen.rey.androiderestaurant.databinding.ActivityListBinding

enum class LunchType {
    STARTER, MAIN, DESSERT;

    companion object {
        fun getResString(type: LunchType): Int {
            return when(type) {
                STARTER -> R.string.starter
                MAIN -> R.string.main
                DESSERT -> R.string.dessert
            }
        }
    }
}

class ListActivity : AppCompatActivity() {
    lateinit var binding: ActivityListBinding
    lateinit var currentCategory: LunchType

    val fakeItems = listOf("plat 1", "plat 2", "plat 3", "plat 4")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentCategory = intent.getSerializableExtra(HomeActivity.CategoryType) as? LunchType ?: LunchType.STARTER
        setupTitle()

        Log.d("life cycle", "CategoryActivity onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life cycle", "CategoryActivity onDestroy")
    }

    fun setupTitle() {
        binding.category.text = getString(LunchType.getResString(currentCategory))
    }
}