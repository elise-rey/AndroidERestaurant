package fr.isen.rey.androiderestaurant

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import fr.isen.rey.androiderestaurant.cart.Cart
import fr.isen.rey.androiderestaurant.cart.CartActivity

open class BaseActivity: AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val menuView = menu?.findItem(R.id.cart)?.actionView
        val countText = menuView?.findViewById(R.id.itemCount) as? TextView
        val count = getItemsCount()
        countText?.isVisible = count > 0
        countText?.text = count.toString()

        menuView?.setOnClickListener {
            if (count > 0) {
                val intent = Intent(this, CartActivity::class.java)
                startActivity(intent)
            }
        }

        return true
    }

    override fun onResume() {
        super.onResume()
        invalidateOptionsMenu()
    }

    private fun getItemsCount(): Int {
        val sharedPreferences = getSharedPreferences(Cart.USER_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(Cart.ITEMS_COUNT, 0)
    }
}