package fr.isen.rey.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
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

    val fakeItems = listOf("item 1", "item 2", "item 3", "item 4", "item 5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentCategory = intent.getSerializableExtra(HomeActivity.CategoryType) as? LunchType ?: LunchType.STARTER
        setupTitle()
        setupList()

        Log.d("life cycle", "CategoryActivity onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life cycle", "CategoryActivity onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("life cycle", "CategoryActivity onRestart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life cycle", "CategoryActivity onStop")
    }

    private fun setupTitle() {
        binding.category.text = getString(LunchType.getResString(currentCategory))
    }

    private fun setupList() {
        binding.listOfFood.layoutManager = LinearLayoutManager(applicationContext)
        binding.listOfFood.adapter = ItemAdapter(fakeItems) { selectedItem ->
            showDetails(selectedItem)
        }
    }

    private fun showDetails(item: String) {
        val intent = Intent(applicationContext, DetailsActivity::class.java)
        intent.putExtra(SELECTED_ITEM, item)
        startActivity(intent)
    }

    companion object {
        const val SELECTED_ITEM = "SELECTED_ITEM"
    }
}
