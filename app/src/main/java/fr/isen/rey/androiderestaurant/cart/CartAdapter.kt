package fr.isen.rey.androiderestaurant.cart

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.rey.androiderestaurant.R
import fr.isen.rey.androiderestaurant.databinding.CellCartBinding

class CartAdapter(private val items: List<CartItem>, private val deleteClickListener: (CartItem) -> Unit): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    lateinit var context: Context

    class CartViewHolder(binding: CellCartBinding): RecyclerView.ViewHolder(binding.root) {
        val dishName: TextView = binding.dishView
        val price: TextView = binding.price
        val quantity: TextView = binding.quantityView
        val delete: ImageButton = binding.delete
        val image: ImageView = binding.foodImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        context = parent.context
        return CartViewHolder(CellCartBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = items[position]

        holder.dishName.text = cartItem.dish.name
        holder.price.text = "${cartItem.dish.prices.first().price} €"
        holder.quantity.text = "${context.getString(R.string.quantity)} ${cartItem.quantity}"
        holder.delete.setOnClickListener {
            deleteClickListener.invoke(cartItem)
        }

        Picasso.get()
            .load(cartItem.dish.getThumbnailURL())
            .placeholder(R.drawable.ic_image_not_supported)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.count()
    }
}