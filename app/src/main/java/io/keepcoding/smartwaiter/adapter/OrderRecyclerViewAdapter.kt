package io.keepcoding.smartwaiter.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.keepcoding.smartwaiter.R
import io.keepcoding.smartwaiter.model.Dish

class OrderRecyclerViewAdapter(private val order: Array<Dish>)
    : RecyclerView.Adapter<OrderRecyclerViewAdapter.OrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_dish, parent, false)

        view.setOnClickListener(onClickListener)

        return OrderViewHolder(view)
    }

    var onClickListener: View.OnClickListener? = null

    override fun getItemCount() = order.count()

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bindDish(order[position])
    }

    inner class OrderViewHolder(itemsView: View): RecyclerView.ViewHolder(itemsView) {
        val dish_image = itemsView.findViewById<ImageView?>(R.id.dish_image)
        val dish_name = itemsView.findViewById<TextView>(R.id.dish_name)
        val dish_price = itemsView.findViewById<TextView>(R.id.dish_price)
        val dish_description = itemsView.findViewById<TextView>(R.id.dish_description)
        val context = itemsView.context

        fun bindDish(dish: Dish) {
            dish_image?.setImageResource(dish.thumbnail)
            dish_name?.text = dish.name
            dish_price?.text = context.getString(R.string.dish_price, dish.price)
            dish_description.visibility = View.GONE
        }
    }
}