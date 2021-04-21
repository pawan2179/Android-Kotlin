package com.example.bill_slip_initial.DaoClass

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bill_slip_initial.EntityClass.ItemModelEntity

@Dao
interface DaoClass {
    @Insert
    suspend fun insert(item : ItemModelEntity)

    @Query("Select * from user order by id")
    fun getAllData() : LiveData<List<ItemModelEntity>>

    @Query("Delete from user")
    suspend fun clearData()

    @Query("select count (*) from user")
    fun getCount():LiveData<Int>

    @Query("select sum (cost) from user")
    fun getTotal():LiveData<Double>
}