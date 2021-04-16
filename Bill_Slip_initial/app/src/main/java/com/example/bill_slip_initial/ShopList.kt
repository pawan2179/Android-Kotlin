package com.example.bill_slip_initial

import android.graphics.Bitmap
import kotlin.math.cos

class ShopList(thumbnail: Bitmap, cost: Double, id: Int) {
    private var _thumbnail : Bitmap = thumbnail
    private var _cost : Double = cost
    private var _id : Int = id

    fun getImage() : Bitmap {
        return _thumbnail
    }
    fun getID() : Int {
        return _id
    }
    fun getCost() : Double {
        return _cost
    }
}