package io.keepcoding.smartwaiter.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import io.keepcoding.smartwaiter.R
import io.keepcoding.smartwaiter.adapter.TableListViewAdapter
import io.keepcoding.smartwaiter.model.Table
import io.keepcoding.smartwaiter.model.Tables
import kotlinx.android.synthetic.main.fragment_table_list.*


class TableListFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() =
            TableListFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    interface OnTableListFragmentListener {
        fun onTableSelected(table: Table, position: Int)
    }

    private var listener: OnTableListFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val adapter = TableListViewAdapter(activity as Context, Tables.toArray())

        table_list_view.adapter = adapter

        table_list_view.setOnItemClickListener { _, _, index, _ ->
            listener?.onTableSelected(Tables[index], index)
        }
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
        if (activity is OnTableListFragmentListener) {
            listener = activity
        } else {
            listener = null
        }
    }
}
