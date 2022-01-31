package fr.isen.rey.androiderestaurant.network

import com.google.gson.annotations.SerializedName
import fr.isen.rey.androiderestaurant.network.Category
import java.io.Serializable

class MenuResult(@SerializedName("data") val data: List<Category>): Serializable {
}