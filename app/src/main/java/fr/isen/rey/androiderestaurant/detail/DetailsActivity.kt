package fr.isen.rey.androiderestaurant.detail

import android.annotation.SuppressLint
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import fr.isen.rey.androiderestaurant.BaseActivity
import fr.isen.rey.androiderestaurant.ListActivity
import fr.isen.rey.androiderestaurant.R
import fr.isen.rey.androiderestaurant.cart.Cart
import fr.isen.rey.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.rey.androiderestaurant.network.Dish

class DetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailsBinding
    var currentDish: Dish? = null
    var count: Float = 1f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentDish = intent.getSerializableExtra(ListActivity.SELECTED_ITEM) as? Dish
        setupContent()
        incrementNbItem()
        addToCart()
    }

    @SuppressLint("SetTextI18n")
    private fun setupContent() {
        binding.viewPager.adapter = currentDish?.let { PhotoAdapter(this, it.images) }

        binding.dishTitle.text = currentDish?.name
        binding.ingredients.text = currentDish?.ingredients?.joinToString(", ") { it.name }

        binding.nbItem.text = count.toInt().toString()
        binding.addToCart.text = "${getString(R.string.total)} ${currentDish?.prices?.first()?.price?.toFloat()} €"
    }

    private fun incrementNbItem() {
        binding.lessButton.setOnClickListener {
            if (count == 1f) {
                refreshButton()
            } else {
                count -= 1
                refreshButton()
            }
        }
        binding.moreButton.setOnClickListener {
            count += 1
            refreshButton()
        }
    }

    private fun addToCart() {
        binding.addToCart.setOnClickListener {
            currentDish?.let { dish ->
                val cart = Cart.getCart(this)
                cart.addItem(dish, count.toInt())
                cart.save(this)
                Snackbar.make(binding.root, R.string.snackbarAdd, Snackbar.LENGTH_SHORT).show()
                invalidateOptionsMenu()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun refreshButton() {
        binding.nbItem.text = count.toInt().toString()

        currentDish?.let { dish ->
            val price: Float = dish.prices.first().price.toFloat()
            val totalPrice = price * count
            binding.addToCart.text = "${getString(R.string.total)} $totalPrice €"
        }
    }
}
