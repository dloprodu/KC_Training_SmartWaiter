package io.keepcoding.smartwaiter.fragment

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.keepcoding.smartwaiter.R
import io.keepcoding.smartwaiter.model.Dish
import io.keepcoding.smartwaiter.model.Table
import io.keepcoding.smartwaiter.model.Tables


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
    }

    private var table: Table? = null
    private var listener: OnOrderFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            table = Tables[ it.getInt(ARG_TABLE, 0) ]
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    // fun onButtonPressed(uri: Uri) {
    //    listener?.onDishSelected(...)
    // }

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
}
