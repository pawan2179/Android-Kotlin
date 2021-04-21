package com.example.bill_slip_initial

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bill_slip_initial.EntityClass.ItemModelEntity
import com.example.bill_slip_initial.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var finalResult : Double = 0.0
    private var mthumbnail : Bitmap? = null
    private var flag = false
    private lateinit var viewModel: ViewModel

    companion object {
        var CAMERA_ACTION_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //reset()
        binding.cameraImage.setOnClickListener {
            if(checkCameraPermission()) {
                clickImage()
            }
            else requestPermission()
        }

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(ViewModel::class.java)

        binding.btnOne.setOnClickListener{btnClicked(binding.btnOne)}
        binding.btnTwo.setOnClickListener{btnClicked(binding.btnTwo)}
        binding.btnThree.setOnClickListener{btnClicked(binding.btnThree)}
        binding.btnFour.setOnClickListener{btnClicked(binding.btnFour)}
        binding.btnFive.setOnClickListener{btnClicked(binding.btnFive)}
        binding.btnSix.setOnClickListener{btnClicked(binding.btnSix)}
        binding.btnSeven.setOnClickListener{btnClicked((binding.btnSeven))}
        binding.btnEight.setOnClickListener{btnClicked(binding.btnEight)}
        binding.btnNine.setOnClickListener{btnClicked(binding.btnNine)}
        binding.btnZero.setOnClickListener{btnClicked(binding.btnZero)}
        binding.btnDot.setOnClickListener{btnClicked(binding.btnDot)}

        binding.btnNext.setOnClickListener {
            if(flag==false) {
                Toast.makeText(this, "Click Image first", Toast.LENGTH_LONG).show()
            }
            else {

                val cost_txt = binding.currentAmount.text
                if(cost_txt == "" || cost_txt.toString().toDouble() == 0.0) {
                    Toast.makeText(this, "Please give an amount", Toast.LENGTH_LONG).show()
                }
                else {
                    var cost = cost_txt.toString().toDouble()
                    btnEqualPressed()
                    //val item = ShopList(mthumbnail!!, cost, s_no)

                    val entity = ItemModelEntity(0, cost, mthumbnail!!)
                    viewModel.insertItem(entity)

                    Log.i("insert done : ", "Successfull")

                    viewModel.totalCost.observe(this, Observer {
                        if(it == null)    binding.totalAmount.text = "0"
                        else              binding.totalAmount.text = it.toString()
                    })

                    /*s_no += 1
                    Cart.addItems(item)
                    Cart.addCost(cost)*/
                    binding.currentAmount.text = "0"
                }
            }
        }
        binding.capture.setOnClickListener {
            val intent = Intent(this, ReceiptActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        //reset()
    }

    override fun onResume() {
        super.onResume()
        //reset()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //reset()
    }
    private fun checkCameraPermission() : Boolean{
        return (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") == 0 &&
        ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == 0 &&
        ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0)
    }
    private fun requestPermission() : Unit {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                "android.permission.CAMERA",
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
            ),
            123
        )
    }
    private fun clickImage() {
        intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_ACTION_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK) {
            if(requestCode == CAMERA_ACTION_CODE) {
                mthumbnail = data!!.extras!!.get("data") as Bitmap
                binding.cameraImage.setImageBitmap(mthumbnail)
                binding.cameraImage.setBackgroundResource(0)
                flag=true
            }
        }
    }
    private fun btnClicked(view : View) {
        var value = binding.currentAmount.text.toString()
        if(value == "0")    value = ""
        when(view.id) {
            binding.btnZero.id -> value += "0"
            binding.btnOne.id -> value += "1"
            binding.btnTwo.id -> value += "2"
            binding.btnThree.id -> value += "3"
            binding.btnFour.id -> value += "4"
            binding.btnFive.id -> value += "5"
            binding.btnSix.id -> value += "6"
            binding.btnSeven.id -> value += "7"
            binding.btnEight.id -> value +="8"
            binding.btnNine.id -> value += "9"
            binding.btnDot.id -> value += "."
        }
        binding.currentAmount.text = (value)
    }

    private fun btnEqualPressed() : Unit{
        /*finalResult = 0.0
        val value1 = binding.currentAmount.text.toString()
        if(value1 == "") {
            Toast.makeText(this, "Please enter an amount", Toast.LENGTH_LONG).show()
            return
        }
        var value2 = binding.totalAmount.text.toString()
        if(value2 == "")    value2 = "0"

        finalResult = value2.toDouble() + value1.toDouble()
        //Log.i("Values", "{$value1, $value2, $finalResult}")
        binding.totalAmount.text = finalResult.toString()*/
        binding.cameraImage.setImageBitmap(null)
        flag=false
        binding.cameraImage.setBackgroundResource(R.drawable.ic_baseline_camera_alt_24)
        //finalResult = 0.0
    }
    /*private fun reset() {
        binding.totalAmount.text = Cart.totalCost.toString()
        binding.currentAmount.text = "0"
    }*/
}