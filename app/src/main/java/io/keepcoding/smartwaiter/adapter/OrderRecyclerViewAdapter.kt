package io.keepcoding.smartwaiter.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.keepcoding.smartwaiter.R
import io.keepcoding.smartwaiter.model.OrderDish

class OrderRecyclerViewAdapter(private val order: Array<OrderDish>)
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
        val allergen_gap1 = itemsView.findViewById<ImageView>(R.id.allergen_gap_one)
        val allergen_gap2 = itemsView.findViewById<ImageView>(R.id.allergen_gap_two)
        val allergen_gap3 = itemsView.findViewById<ImageView>(R.id.allergen_gap_three)
        val allergen_gap4 = itemsView.findViewById<ImageView>(R.id.allergen_gap_four)
        val allergen_gap5 = itemsView.findViewById<ImageView>(R.id.allergen_gap_five)
        val allergen_gap6 = itemsView.findViewById<ImageView>(R.id.allergen_gap_six)
        val context = itemsView.context

        fun bindDish(orderDish: OrderDish) {
            dish_image?.setImageResource(orderDish.dish.thumbnail)
            dish_name?.text = orderDish.dish.name
            dish_price?.text = context.getString(R.string.dish_price, orderDish.dish.price)
            dish_description?.visibility = View.GONE

            for ((index, value) in orderDish.dish.allergens.withIndex()) {
                when (index) {
                    0 -> allergen_gap1
                    1 -> allergen_gap2
                    2 -> allergen_gap3
                    3 -> allergen_gap4
                    4 -> allergen_gap5
                    else -> allergen_gap6
                }?.setImageResource(value.icon)
            }
        }
    }
}