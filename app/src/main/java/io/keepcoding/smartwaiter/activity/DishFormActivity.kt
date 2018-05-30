package io.keepcoding.smartwaiter.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.transition.Explode
import android.transition.Slide
import android.view.Gravity
import android.view.MenuItem
import android.view.Window
import io.keepcoding.smartwaiter.R
import io.keepcoding.smartwaiter.model.Dishes
import kotlinx.android.synthetic.main.content_dish.*

class DishFormActivity : AppCompatActivity() {

    companion object {
        val EXTRA_TABLE_INDEX = "EXTRA_TABLE_INDEX"
        val EXTRA_DISH_INDEX = "EXTRA_DISH_INDEX"

        fun intent(context: Context, tableIndex: Int, dishIndex: Int): Intent {
            val intent = Intent(context, DishFormActivity::class.java)
            intent.putExtra(EXTRA_TABLE_INDEX, tableIndex)
            intent.putExtra(EXTRA_DISH_INDEX, dishIndex)
            return intent
        }
    }

    private var tableIndexSelected: Int = 0
    private var dishIndexSelected: Int = 0

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
        setContentView(R.layout.activity_dish_form)

        this.tableIndexSelected = intent.getIntExtra(DishFormActivity.EXTRA_TABLE_INDEX,0)
        this.dishIndexSelected = intent.getIntExtra(DishFormActivity.EXTRA_DISH_INDEX,0)

        val dish = Dishes[this.dishIndexSelected]

        dish_image.setImageResource(dish.thumbnail)
        dish_name.text = dish.name;
        dish_price.text = getString(R.string.dish_price, dish.price)
        dish_description.text = dish.description


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
