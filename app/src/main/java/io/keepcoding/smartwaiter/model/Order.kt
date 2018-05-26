package io.keepcoding.smartwaiter.model

import java.io.Serializable

data class Order(var table: Table, var dishes: MutableList<Dish>, var comments: String) : Serializable {

    override fun toString(): String = "Order for ${table.name}"

    fun getTotalPrice(): Double {
        // TODO
        return 0.0
    }

    val count
        get() = dishes.size

    fun toArray() = dishes.toTypedArray()

    operator fun get(i: Int) = dishes[i]

    // Para poder iterar como si fuera una lista
    operator fun iterator() = dishes.iterator()

    operator fun plus(city: Dish) {
        dishes.add(city)
    }
}