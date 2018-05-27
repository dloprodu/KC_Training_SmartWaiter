package io.keepcoding.smartwaiter.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.keepcoding.smartwaiter.R
import io.keepcoding.smartwaiter.adapter.OrderRecyclerViewAdapter
import io.keepcoding.smartwaiter.model.Dish
import io.keepcoding.smartwaiter.model.Order
import io.keepcoding.smartwaiter.model.Table
import io.keepcoding.smartwaiter.model.Tables
import kotlinx.android.synthetic.main.content_order.*
import kotlinx.android.synthetic.main.fragment_order.*


@Suppress("UNREACHABLE_CODE")
class OrderFragment : Fragment() {

    companion object {
        val ARG_TABLE = "ARG_TABBLE"
        fun newInstance(tableIndex: Int) =
            OrderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TABLE, tableIndex)
                }
            }
    }

    // Interface use to communicate us with Activities
    interface OnOrderFragmentListener {
        fun onDishSelected(dish: Dish, position: Int)
        fun onAddNewDish(table: Table, position: Int)
    }

    var order: Order? = null
        set(value) {
            field = value

            if (value != null && value.count > 0) {
                val adapter = OrderRecyclerViewAdapter(value.dishes.toTypedArray())

                dish_list.adapter = adapter
                dish_list.visibility = View.VISIBLE;
                create_new_order_message.visibility = View.GONE;

                setRecyclerViewClickListener()
            } else {
                dish_list.adapter = null

                dish_list.visibility = View.GONE;
                create_new_order_message.visibility = View.VISIBLE;
            }
        }

    private var table: Table? = null
    private var listener: OnOrderFragmentListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            showOrder(it.getInt(ARG_TABLE, 0))
        }

        fab_create.setOnClickListener { view ->
            arguments?.let {
                val tableIndex = it.getInt(ARG_TABLE, 0)
                listener?.onAddNewDish(Tables[tableIndex], tableIndex)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    fun showOrder(tableIndex: Int) {
        order = Tables[tableIndex].order

        create_new_order_message.text = this.activity?.getString(R.string.create_new_order, Tables[tableIndex].name)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonOnAttach(context as Activity)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonOnAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun commonOnAttach(activity: Activity?) {
        if (activity is OnOrderFragmentListener) {
            listener = activity
        } else {
            listener = null
        }
    }

    fun setRecyclerViewClickListener() {
        val adapter = dish_list?.adapter as? OrderRecyclerViewAdapter
        adapter?.onClickListener = View.OnClickListener {
            // Alguien ha pulsado un elemento del RecyclerView
            val forecastIndex = dish_list.getChildAdapterPosition(it)
            /*

            TODO

            val city = arguments?.getSerializable(ARG_CITY) as City
            val cityIndex = Cities.getIndex(city)

            // Opciones especiales para navegar con vistas comunes
            val animationOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity,
                    it,
                    getString(R.string.transition_to_detail)
            )

            startActivity(DetailActivity.intent(activity!!, cityIndex, forecastIndex), animationOptions.toBundle())
            */
        }
    }
}
