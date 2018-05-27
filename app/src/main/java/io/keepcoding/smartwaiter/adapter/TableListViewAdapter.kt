package io.keepcoding.smartwaiter.adapter

import android.content.Context;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import io.keepcoding.smartwaiter.R
import io.keepcoding.smartwaiter.model.Table

class TableListViewAdapter(context: Context, tables: Array<Table>) : BaseAdapter() {

    private val context: Context
    private val inflator : LayoutInflater
    private val tables : Array<Table>

    init {
        this.context = context
        this.inflator = LayoutInflater.from(context)
        this.tables = tables
    }

    override fun getCount(): Int {
        return tables.size
    }

    override fun getItem(position: Int): Any {
        return tables.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val viewHolder: TableListViewHolder

        if (convertView == null) {
            view = this.inflator.inflate(R.layout.table_cell, parent, false)
            viewHolder = TableListViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as TableListViewHolder
        }

        val table = tables[position]

        viewHolder.title_item.text = table.name
        viewHolder.desc_item.text = this.context.getString(R.string.table_cell_description,
                if (table.isAvailable) context.getString( android.R.string.yes ) else context.getString( android.R.string.no ),
                table.minDiners.toString(),
                table.maxDiners.toString()
                )
        viewHolder.image_item.setImageResource(table.icon)

        return view
    }

    private class TableListViewHolder(row: View?) {
        val title_item : TextView
        val desc_item : TextView
        val image_item : ImageView

        init {
            this.title_item = row?.findViewById<TextView>(R.id.table_cell_name) as TextView
            this.desc_item = row?.findViewById<TextView>(R.id.table_cell_description) as TextView
            this.image_item = row?.findViewById<ImageView>(R.id.table_cell_image) as ImageView
        }
    }
}