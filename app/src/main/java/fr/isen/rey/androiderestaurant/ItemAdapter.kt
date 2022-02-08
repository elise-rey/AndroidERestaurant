package fr.isen.rey.androiderestaurant

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.squareup.picasso.Picasso
import fr.isen.rey.androiderestaurant.databinding.CellFoodBinding
import fr.isen.rey.androiderestaurant.network.Dish

class ItemAdapter(private val listItem: List<Dish>, private val itemClickListener: (Dish) -> Unit): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(binding: CellFoodBinding): RecyclerView.ViewHolder(binding.root) {
        val foodItem: TextView = binding.foodView
        val priceItem: TextView = binding.priceView
        val imageItem: ImageView = binding.imageView
        val layout: CardView = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = CellFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = listItem[position]
        holder.foodItem.text = item.name
        holder.priceItem.text = "${item.prices.first().price}€"
        Picasso
            .get()
            .load(item.getThumbnailURL())
            .placeholder(R.drawable.no_photo)
            .into(holder.imageItem)
        holder.layout.setOnClickListener {
            itemClickListener.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return listItem.count()
    }
}