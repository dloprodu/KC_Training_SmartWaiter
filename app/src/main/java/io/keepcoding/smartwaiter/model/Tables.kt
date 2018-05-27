package io.keepcoding.smartwaiter.model

import io.keepcoding.smartwaiter.R

object Tables {
    private var tables: List<Table> = listOf(
            Table("Mesa 1", 1, 2, R.drawable.table_48x48),
            Table("Mesa 2", 2, 2, R.drawable.table_48x48),
            Table("Mesa 3", 1, 2, R.drawable.table_48x48),
            Table("Mesa 4", 2, 4, R.drawable.table_48x48),
            Table("Mesa 5", 2, 4, R.drawable.table_48x48),
            Table("Mesa 6", 4, 6, R.drawable.table_48x48),
            Table("Mesa 7", 2, 4, R.drawable.table_48x48),
            Table("Mesa 8", 2, 4, R.drawable.table_48x48),
            Table("Mesa 9", 2, 4, R.drawable.table_48x48),
            Table("Mesa 10", 4, 6, R.drawable.table_48x48),
            Table("Mesa 11", 1, 2, R.drawable.table_48x48),
            Table("Mesa 12", 4, 6, R.drawable.table_48x48)
    )

    val count
        get() = tables.size

    fun toArray() = tables.toTypedArray()

    operator fun get(i: Int) = tables[i]

    // Para poder iterar como si fuera una lista
    operator fun iterator() = tables.iterator()
}