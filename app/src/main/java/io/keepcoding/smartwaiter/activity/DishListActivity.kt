package io.keepcoding.smartwaiter.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.transition.Explode
import android.transition.Slide
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.Window
import io.keepcoding.smartwaiter.R
import io.keepcoding.smartwaiter.adapter.DishRecyclerViewAdapter
import io.keepcoding.smartwaiter.model.Dishes
import kotlinx.android.synthetic.main.activity_dish_list.*

class DishListActivity : AppCompatActivity() {

    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun intent(context: Context, tableIndex: Int): Intent {
            val intent = Intent(context, DishListActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)
            return intent
        }
    }

    private var tableIndexSelected: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        // Indicamos la animación de entrada, y la de regreso a la anterior
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            val explode = Explode()
            explode.duration = 1000
            window.enterTransition = explode

            val slide = Slide(Gravity.RIGHT)
            slide.duration = 1000
            window.returnTransition = slide
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish_list)

        this.tableIndexSelected = intent.getIntExtra(EXTRA_TABLE_INDEX,0)

        // Configuramos el RecycleView.
        // - Primero decimos como se visualizan sus elementos
        dish_list.layoutManager = GridLayoutManager(this, resources.getInteger( R.integer.dish_columns )) // LinearLayoutManager(activity)

        // - Le decimos quien es el que anima al RecyclerView
        dish_list.itemAnimator = DefaultItemAnimator()

        dish_list.adapter = DishRecyclerViewAdapter(Dishes.toArray())

        (dish_list.adapter as DishRecyclerViewAdapter).onClickListener = View.OnClickListener { v: View? -> Unit
            val dishIndex = dish_list.getChildAdapterPosition(v)
            startActivity(DishFormActivity.intent(this, tableIndexSelected, dishIndex))
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when(item?.itemId) {
        android.R.id.home -> { endActivity(); true }
        else -> super.onOptionsItemSelected(item)
    }

    fun endActivity() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
        else {
            // Esto me asegura que finaliza la actividad actual y regresa a la que
            // hayamos definido en el AndroidManifest.xml
            // Sirve si pudiera acceder a CityPagerActivity desde varios sitios
            // y no quiero marear al usuario con la navegación
            // Ejemplo: correo nuevo de Gmail
            NavUtils.navigateUpFromSameTask(this);
        }
    }
}
