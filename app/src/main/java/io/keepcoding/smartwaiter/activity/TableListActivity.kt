package io.keepcoding.smartwaiter.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import io.keepcoding.smartwaiter.R
import io.keepcoding.smartwaiter.fragment.OrderFragment
import io.keepcoding.smartwaiter.fragment.TableListFragment
import io.keepcoding.smartwaiter.model.Dish
import io.keepcoding.smartwaiter.model.Table

class TableListActivity : AppCompatActivity(), TableListFragment.OnTableListFragmentListener, OrderFragment.OnOrderFragmentListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table_list)

        // Averiguamos qué interfaz hemos cargado
        // Eso lo averiguamos preguntando si en la interfaz tenermos un FrameLayout concreto
        if (findViewById<ViewGroup>(R.id.table_list_fragment) != null) {
            // Hemos cargado una interfaz que tiene el hueco para el fragment CityLilstFragment
            // Comprobamos primero que no tenemos ya añadido el fragment a nuestra jerarquía
            if (fragmentManager.findFragmentById(R.id.table_list_fragment)  == null) {
                // Añadir dinámicamente el fragment a la interfaz
                fragmentManager.beginTransaction()
                        .add(R.id.table_list_fragment, TableListFragment.newInstance())
                        .commit()
            }
        }

        if (findViewById<ViewGroup>(R.id.order_fragment) != null) {
            // Hemos cargado una interfaz que tiene el hueco para el fragment CityPagerFragment
            if (fragmentManager.findFragmentById(R.id.order_fragment) == null) {
                fragmentManager.beginTransaction()
                        .add(R.id.order_fragment, OrderFragment.newInstance(0))
                        .commit()
            }
        }
    }

    override fun onTableSelected(table: Table, position: Int) {
        if (findViewById<ViewGroup>(R.id.order_fragment) != null) {
            val orderFragment = fragmentManager.findFragmentById(R.id.order_fragment) as? OrderFragment
            if (orderFragment != null) {
                orderFragment.showOrder(position)
            }
        } else {
            val intent = OrderActivity.intent(this, position)
            startActivity(intent)
        }
    }

    override fun onDishSelected(dish: Dish, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAddNewDish(table: Table, position: Int) {
        val intent = DishListActivity.intent(this, position)
        startActivity(intent)
    }
}
