package io.keepcoding.smartwaiter.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.transition.Explode
import android.transition.Slide
import android.view.Gravity
import android.view.MenuItem
import android.view.ViewGroup
import android.view.Window
import io.keepcoding.smartwaiter.R
import io.keepcoding.smartwaiter.fragment.OrderFragment
import io.keepcoding.smartwaiter.model.Dish
import io.keepcoding.smartwaiter.model.Table
import io.keepcoding.smartwaiter.model.Tables

import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity(), OrderFragment.OnOrderFragmentListener {

    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"

        fun intent(context: Context, tableIndex: Int): Intent {
            val intent = Intent(context, OrderActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)
            return intent
        }
    }

    val toolbar: Toolbar
        get() = findViewById<Toolbar>(R.id.toolbar)

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
        setContentView(R.layout.activity_order)
        setSupportActionBar(toolbar)

        val tableIndex = intent.getIntExtra(EXTRA_TABLE_INDEX, 0);

        if (findViewById<ViewGroup>(R.id.order_fragment) != null) {
            // Hemos cargado una interfaz que tiene el hueco para el fragment CityPagerFragment
            if (supportFragmentManager.findFragmentById(R.id.order_fragment) == null) {
                supportFragmentManager.beginTransaction()
                        .add(R.id.order_fragment, OrderFragment.newInstance(tableIndex))
                        .commit()
            }
        }

        val table = Tables[ tableIndex ]

        // Vamos a decirle a la actividad que use nuestra toolbar personalizada
        // toolbar.setLogo(R.mipmap.ic_launcher)
        toolbar.title = table.name
        setSupportActionBar(toolbar)

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

    override fun onDishSelected(dish: Dish, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTableChange(table: Table, position: Int) {
        toolbar.title = table.name
    }
}
