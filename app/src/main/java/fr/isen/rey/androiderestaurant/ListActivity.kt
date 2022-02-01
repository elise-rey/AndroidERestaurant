package fr.isen.rey.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.rey.androiderestaurant.databinding.ActivityListBinding
import fr.isen.rey.androiderestaurant.detail.DetailsActivity
import fr.isen.rey.androiderestaurant.network.Dish
import fr.isen.rey.androiderestaurant.network.MenuResult
import fr.isen.rey.androiderestaurant.network.NetworkConstants
import org.json.JSONObject

enum class LunchType {
    STARTER, MAIN, DESSERT;

    companion object {
        fun getResString(type: LunchType): Int {
            return when (type) {
                STARTER -> R.string.starter
                MAIN -> R.string.main
                DESSERT -> R.string.dessert
            }
        }

        fun getCategoryTitle(type: LunchType): String {
            return when (type) {
                STARTER -> "Entrées"
                MAIN -> "Plats"
                DESSERT -> "Desserts"
            }
        }
    }
}

class ListActivity : BaseActivity() {
    lateinit var binding: ActivityListBinding
    lateinit var currentCategory: LunchType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentCategory = intent.getSerializableExtra(HomeActivity.CategoryType) as? LunchType ?: LunchType.STARTER
        setupTitle()
        makeRequest()

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

    private fun setupList(items: List<Dish>) {
        binding.listOfFood.layoutManager = LinearLayoutManager(this)
        binding.listOfFood.adapter = ItemAdapter(items) { selectedItem ->
            showDetails(selectedItem)
        }
    }

    private fun showDetails(item: Dish) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(SELECTED_ITEM, item)
        startActivity(intent)
    }

    private fun makeRequest() {
        val queue = Volley.newRequestQueue(this)
        val url = NetworkConstants.BASE_URL + NetworkConstants.MENU
        val parameters = JSONObject()
        parameters.put(NetworkConstants.KEY_SHOP, NetworkConstants.SHOP)
        val request = JsonObjectRequest(Request.Method.POST,
            url,
            parameters,
            {
                parseResult(it.toString())
            },
            {
                Log.d("debug", "$it")
            }
        )
        queue.add(request)
    }

    private fun parseResult(json: String) {
        val result = GsonBuilder().create().fromJson(json, MenuResult::class.java)
        val items = result.data.firstOrNull {
            it.name == LunchType.getCategoryTitle(currentCategory)
        }?.items

        items?.let {
            setupList(it)
        }
    }

    companion object {
        const val SELECTED_ITEM = "SELECTED_ITEM"
    }
}
