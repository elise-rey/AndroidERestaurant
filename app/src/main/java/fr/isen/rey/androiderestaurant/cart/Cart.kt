package fr.isen.rey.androiderestaurant.cart

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import fr.isen.rey.androiderestaurant.network.Dish
import java.io.File
import java.io.Serializable

class Cart(private val items: MutableList<CartItem>): Serializable {
    var itemsCount: Int = 0
        get() {
            return items.map {
                it.quantity
            }.reduceOrNull { acc, i -> acc + i } ?: 0
        }

    var totalPrice: Float = 0f
        get() {
            return items.map {
                it.quantity * it.dish.prices.first().price.toFloat()
            }.reduceOrNull { acc, f -> acc + f } ?: 0f
        }

    fun addItem(item: Dish, quantity: Int) {
        val existingItem = items.firstOrNull{ it.dish.name == item.name }
        existingItem?.let {
            existingItem.quantity += quantity
        } ?: run {
            val cartItem = CartItem(item, quantity)
            items.add(cartItem)
        }
    }

    fun save(context: Context) {
        val jsonFile = File(context.cacheDir.absolutePath + CART_FILE)
        val json = GsonBuilder().create().toJson(this)
        jsonFile.writeText(json)
    }

    private fun updateCount(context: Context) {
        val sharedPreferences = context.getSharedPreferences(USER_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(ITEMS_COUNT, itemsCount)
        editor.apply()
    }

    companion object {
        fun getCart(context: Context): Cart {
            val jsonFile = File(context.cacheDir.absolutePath + CART_FILE)
            return if(jsonFile.exists()) {
                val json = jsonFile.readText()
                GsonBuilder().create().fromJson(json, Cart::class.java)
            } else {
                Cart(mutableListOf())
            }
        }

        const val CART_FILE = "cart.json"
        const val ITEMS_COUNT = "ITEMS_COUNT"
        const val USER_PREFERENCES_NAME = "USER_PREFERENCES_NAME"
    }
}

class CartItem(val dish: Dish, var quantity: Int): Serializable {

}