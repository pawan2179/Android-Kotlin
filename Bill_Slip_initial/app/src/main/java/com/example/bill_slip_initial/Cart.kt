package com.example.bill_slip_initial

object Cart {
    var orderList = ArrayList<ShopList> ()
    var totalCost : Double = 0.0
    var totalItem : Int = 0

    fun getItems() : ArrayList<ShopList> {
        return orderList
    }
    fun addItems(shopList: ShopList) {
        orderList.add(shopList)
        totalItem++
    }
    fun addCost(cost : Double) {
        totalCost += cost
    }
    fun getTotal() : Double {
        return totalCost
    }
}