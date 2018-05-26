package io.keepcoding.smartwaiter.model

import java.io.Serializable

data class Table(var name: String, var minDiners: Int, var maxDiners: Int, var icon: Int, var order: Order? = null) : Serializable {

    override fun toString(): String = name

    fun createOrder() {
        order = Order(this, mutableListOf(), "")
    }

    fun free() {
        order = null
    }
}