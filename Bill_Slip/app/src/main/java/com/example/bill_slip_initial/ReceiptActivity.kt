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
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReceiptActivity : AppCompatActivity() {

    private lateinit var _recyclerView : RecyclerView
    private lateinit var txt_view_not_found : TextView
    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reciept)

        val txtItemCount : TextView = findViewById(R.id.bottom_bar2_total_items)
        val txtItemTotal : TextView = findViewById(R.id.bottom_bar2_total_amount)
        val txtTotalView : TextView = findViewById(R.id.txt_total_cost_view)
        val itemDao = DatabaseClass.getDatabase(this)?.getDao()

        //Toast.makeText(this, "Retrieved successfully {${list!!.size} items", Toast.LENGTH_LONG).show()

        _recyclerView = findViewById(R.id.recycler_view)
        txt_view_not_found = findViewById(R.id.txt_view_no_items)

        _recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ReceiptAdapter(this)
        _recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ViewModel::class.java)

        viewModel.allItems.observe(this, Observer {
            if(it == null) {
                txt_view_not_found.visibility = View.VISIBLE
            }
            else {
                txt_view_not_found.visibility = View.GONE
                adapter.updateList(it)
            }
        })

        viewModel.totalCost.observe(this, Observer {
            if(it == null) {
                txtItemTotal.text = "0"
                txtTotalView.text = "0"
            }
            else {
                txtTotalView.text = it.toString()
                txtItemTotal.text = it.toString()
            }
        })

        viewModel.itemCount.observe(this, Observer {
            if(it == null)    txtItemCount.text = "0"
            else              txtItemCount.text = it.toString()
        })

        val clearBtn : Button = findViewById(R.id.btn_clear)
        clearBtn.setOnClickListener {
            viewModel.delete()
        }



        /*val orders : ArrayList<ShopList> = Cart.getItems()
        val itemCount = orders.size

        if(itemCount > 0)    txt_view_not_found.visibility = View.GONE



        txtItemTotal.text = (Cart.getTotal().toString())
        txtItemCount.text = (itemCount.toString())

        Log.i("Orders", "{$orders}")*/


        val orderBtn : ImageView = findViewById(R.id.capture)
        orderBtn.setOnClickListener {
            val intent = Intent(this, InvoiceActivity::class.java)
            startActivity(intent)
        }
    }
    /*private fun clearData() {
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

    }*/
}