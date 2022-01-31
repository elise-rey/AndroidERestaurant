package fr.isen.rey.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import fr.isen.rey.androiderestaurant.databinding.CellFoodBinding
import fr.isen.rey.androiderestaurant.network.Dish

class ItemAdapter(val listItem: List<Dish>, val itemClickListener: (Dish) -> Unit): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(binding: CellFoodBinding): RecyclerView.ViewHolder(binding.root) {
        val foodItem: TextView = binding.foodView
        val layout: CardView = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = CellFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = listItem[position]
        holder.foodItem.text = item.name
        holder.layout.setOnClickListener {
            itemClickListener.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return listItem.count()
    }
}