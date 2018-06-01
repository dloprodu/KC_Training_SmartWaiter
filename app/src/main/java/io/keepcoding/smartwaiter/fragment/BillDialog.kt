package io.keepcoding.smartwaiter.fragment

import android.app.Activity
import android.app.AlertDialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import io.keepcoding.smartwaiter.R
import io.keepcoding.smartwaiter.model.Order
import kotlinx.android.synthetic.main.dialog_bill.*

class BillDialog : DialogFragment() {

    companion object {

        const val ARG_PAID = "ARG_PAID"
        const val ARG_ORDER = "ARG_ORDER"

        fun newInstance(order: Order): BillDialog {
            val arguments = Bundle()
            arguments.putSerializable(ARG_ORDER, order)

            val fragment = BillDialog()
            fragment.arguments = arguments

            return fragment
        }
    }

    lateinit var textView: TextView
    val orden by lazy<Order?> { arguments.getSerializable(ARG_ORDER) as? Order }

    override fun onCreateDialog(savedInstanceState: Bundle?) = AlertDialog.Builder(activity)
            .setTitle(getString(R.string.cuenta))
            .setView(initialiseView())
            .setPositiveButton(android.R.string.ok, DialogInterface.OnClickListener { _, _ -> acceptSettings() })
            .setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { _, _ -> cancelSettings() })
            .create()


    fun  initialiseView(): View {
        val view = activity.layoutInflater.inflate(R.layout.dialog_bill, null, false)

        var bill = 0.0
        if (this.orden != null) {
            for (item in this.orden!!.dishes) {
                bill += item.dish.price
            }
        }

        textView = view.findViewById<TextView>(R.id.text_cuenta)
        textView?.text = getString(R.string.total_a_abonar, bill)

        return view
    }

    private fun cancelSettings() {
        targetFragment.onActivityResult(targetRequestCode, Activity.RESULT_CANCELED, null)
        dismiss()
    }

    private fun acceptSettings() {
        val returnIntent = Intent()
        returnIntent.putExtra(ARG_PAID, true)

        targetFragment.onActivityResult(targetRequestCode, Activity.RESULT_OK, returnIntent)
        dismiss()
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when(item?.itemId) {
        android.R.id.home -> { cancelSettings(); true }
        else -> super.onOptionsItemSelected(item)
    }
}