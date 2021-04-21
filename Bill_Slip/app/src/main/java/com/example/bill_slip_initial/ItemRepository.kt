package com.example.bill_slip_initial

import androidx.lifecycle.LiveData
import com.example.bill_slip_initial.DaoClass.DaoClass
import com.example.bill_slip_initial.EntityClass.ItemModelEntity

class ItemRepository(private val itemDao : DaoClass) {

    val allItems : LiveData<List<ItemModelEntity>> = itemDao.getAllData()
    val itemCount : LiveData<Int> = itemDao.getCount()
    val totalCost : LiveData<Double> = itemDao.getTotal()

    suspend fun insert(item : ItemModelEntity) {
        itemDao.insert(item)
    }

    suspend fun delete() {
        itemDao.clearData()
    }

    /*suspend fun getCount() {
        itemDao.getCount()
    }

    suspend fun getTotal() {
        itemDao.getTotal()
    }*/
}