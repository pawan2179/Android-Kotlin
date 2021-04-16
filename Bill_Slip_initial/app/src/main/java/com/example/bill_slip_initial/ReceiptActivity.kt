package com.example.bill_slip_initial

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReceiptActivity : AppCompatActivity() {

    private lateinit var _recyclerView : RecyclerView
    private lateinit var txt_view_not_found : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reciept)

        val clearAll : Button = findViewById(R.id.btn_clear);
        clearAll.setOnClickListener {
            clearData();
        }

        val finalCost : TextView = findViewById(R.id.txt_total_cost_view)
        finalCost.text = (Cart.getTotal().toString())
        _recyclerView = findViewById(R.id.recycler_view)
        txt_view_not_found = findViewById(R.id.txt_view_no_items)

        val orders : ArrayList<ShopList> = Cart.getItems()
        val itemCount = orders.size

        if(itemCount > 0)    txt_view_not_found.visibility = View.GONE

        val txtItemCount : TextView = findViewById(R.id.bottom_bar2_total_items)
        val txtItemTotal : TextView = findViewById(R.id.bottom_bar2_total_amount)

        txtItemTotal.text = (Cart.getTotal().toString())
        txtItemCount.text = (itemCount.toString())

        Log.i("Orders", "{$orders}")

        _recyclerView.adapter = ReceiptAdapter(orders)
        _recyclerView.layoutManager = LinearLayoutManager(this)
        _recyclerView.setHasFixedSize(true)

        val orderBtn : ImageView = findViewById(R.id.capture)
        orderBtn.setOnClickListener {
            val intent = Intent(this, InvoiceActivity::class.java)
            startActivity(intent)
        }
    }
    private fun clearData() {
        Cart.orderList.clear()
        Cart.totalItem = 0
        Cart.totalCost = 0.0
        updateViews()
        _recyclerView.visibility = View.GONE
        txt_view_not_found.visibility = View.VISIBLE

    }
    private fun updateViews() {
        val txtItemCount : TextView = findViewById(R.id.bottom_bar2_total_items)
        val txtItemTotal : TextView = findViewById(R.id.bottom_bar2_total_amount)
        val txtTotal : TextView = findViewById(R.id.txt_total_cost_view)

        txtItemTotal.text = (Cart.getTotal().toString())
        txtItemCount.text = (Cart.totalItem.toString())
        txtTotal.text = Cart.totalCost.toString()

    }
}