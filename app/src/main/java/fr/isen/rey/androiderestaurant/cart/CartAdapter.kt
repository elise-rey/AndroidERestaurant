package fr.isen.rey.androiderestaurant.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.rey.androiderestaurant.R
import fr.isen.rey.androiderestaurant.databinding.CellCartBinding

class CartAdapter(private val items: List<CartItem>): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    class CartViewHolder(binding: CellCartBinding): RecyclerView.ViewHolder(binding.root) {
        val dishName: TextView = binding.dishView
        val price: TextView = binding.price
        val quantity: TextView = binding.quantityView
        val delete: ImageButton = binding.delete
        val image: ImageView = binding.foodImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(CellCartBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = items[position]
        holder.dishName.text = cartItem.dish.name
        holder.price.text = cartItem.dish.prices.first().price
        holder.quantity.text = cartItem.quantity.toString()
        Picasso.get()
            .load(cartItem.dish.getThumbnailURL())
            .placeholder(R.drawable.no_photo)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}