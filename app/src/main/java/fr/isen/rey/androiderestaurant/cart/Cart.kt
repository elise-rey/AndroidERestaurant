package fr.isen.rey.androiderestaurant.cart

import fr.isen.rey.androiderestaurant.network.Dish
import java.io.Serializable

class Cart: Serializable {
}

class CartItem(val dish: Dish, var quantity: Int): Serializable {
    
}