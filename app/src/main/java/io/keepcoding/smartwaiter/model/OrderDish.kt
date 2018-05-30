package io.keepcoding.smartwaiter.model

data class OrderDish(private val dishIndex: Int, val comments: String) {
    val dish
        get() = Dishes[dishIndex]
}