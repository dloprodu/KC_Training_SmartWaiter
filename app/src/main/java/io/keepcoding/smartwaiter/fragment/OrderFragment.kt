package io.keepcoding.smartwaiter.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*

import io.keepcoding.smartwaiter.R
import io.keepcoding.smartwaiter.adapter.OrderRecyclerViewAdapter
import io.keepcoding.smartwaiter.model.*
import kotlinx.android.synthetic.main.content_order.*
import kotlinx.android.synthetic.main.fragment_order.*

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

    val REQUEST_ORDER = 1
    var tableIndexSelected = 0

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

    private var listener: OnOrderFragmentListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            tableIndexSelected = it.getInt(ARG_TABLE, 0)
            showOrder(tableIndexSelected)
        }

        // Configuramos el RecycleView.
        // - Primero decimos como se visualizan sus elementos
        dish_list.layoutManager = GridLayoutManager(activity, resources.getInteger( R.integer.dish_columns )) as RecyclerView.LayoutManager? // LinearLayoutManager(activity)

        // - Le decimos quien es el que anima al RecyclerView
        dish_list.itemAnimator = DefaultItemAnimator()

        fab_create.setOnClickListener { view ->
            arguments?.let {
                listener?.onAddNewDish(Tables[tableIndexSelected], tableIndexSelected)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_order, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_show_bill -> {
                if (this.order == null) {
                    return false
                }

                val trx = fragmentManager.beginTransaction()
                val prev = fragmentManager.findFragmentByTag("bill")
                if (prev != null) {
                    trx?.remove(prev)
                }
                trx?.addToBackStack(null)

                val dialog = BillDialog.newInstance(this.order!!)

                dialog.setTargetFragment(this, REQUEST_ORDER)
                dialog.show(fragmentManager, "bill")

                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_ORDER -> {
                // Estamos volviendo de la pantalla de SettingsDialog
                // Miro cómo ha ido el resultado
                when (resultCode) {
                    AppCompatActivity.RESULT_OK -> {
                        val table = order!!.table;
                        table.order = null;
                        order = null;
                    }
                    AppCompatActivity.RESULT_CANCELED -> {
                        // No hago nada porque el usuario ha cancelado la selección de unidades
                    }
                }
            }
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        showOrder(tableIndexSelected)
    }

    override fun onResume() {
        super.onResume()
        showOrder(tableIndexSelected)
    }

    fun showOrder(tableIndex: Int) {
        tableIndexSelected = tableIndex
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

            /*
            val forecastIndex = dish_list.getChildAdapterPosition(it)
            TODO

            */
        }
    }
}
