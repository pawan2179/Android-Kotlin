package com.example.bill_slip_initial

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.w3c.dom.Text

class InvoiceActivity : AppCompatActivity() {

    private lateinit var whatsapp : ImageView
    private lateinit var telegram : ImageView
    private lateinit var message : ImageView
    private lateinit var phNo : EditText
    private lateinit var viewModel:ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice)

        phNo = findViewById(R.id.phone_number)
        whatsapp = findViewById(R.id.btn_whatsapp)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ViewModel::class.java)
        /*val totalCost = Cart.getTotal()
        val itemCount = Cart.totalItem.toString()

        val item = findViewById<TextView>(R.id.total_item_count)
        item.setText(itemCount)*/

        val total = findViewById<TextView>(R.id.total_amount)
        val count = findViewById<TextView>(R.id.total_item_count)
        var totalCost = 0.0

        viewModel.totalCost.observe(this, Observer {
            if(it == null) {
                total.text = "0"
            }
            else {
                total.text = it.toString()
            }
        })

        viewModel.itemCount.observe(this, Observer {
            if(it == null)    count.text = "0"
            else              count.text = it.toString()
        })

        var message = "You have just purchased items worth ₹ $totalCost from General Store. Please find below the pdf. To proceed with your payments, click on below link:\n\n" +
                "1. Google Pay\n2. Bhim\n3. BharatPe\n4. PhonePay\n4. Card(razorpay)\n"

        whatsapp.setOnClickListener {
            if(phNo.text.toString().length == 10) {
                intent = Intent("android.intent.action.VIEW")
                intent.setData(Uri.parse("https://api.whatsapp.com/send?text=$message &phone=91" + phNo.text.toString()))
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_LONG).show()
            }
        }
    }


}