package io.keepcoding.smartwaiter.model

import java.io.Serializable


data class Dish(var name: String, var description: String, var price: Double, var thumbnail: Int, var allergens: List<Allergen>?) : Serializable {

    // constructor(name: String) : this(name)

    override fun toString(): String = name
}