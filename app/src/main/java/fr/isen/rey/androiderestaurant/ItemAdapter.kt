package fr.isen.rey.androiderestaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import fr.isen.rey.androiderestaurant.databinding.CellFoodBinding

class ItemAdapter(val listItem: List<String>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(binding: CellFoodBinding): RecyclerView.ViewHolder(binding.root) {
        val foodItem: TextView = binding.foodView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = CellFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = listItem[position]
        holder.foodItem.text = item
    }

    override fun getItemCount(): Int {
        return listItem.count()
    }
}