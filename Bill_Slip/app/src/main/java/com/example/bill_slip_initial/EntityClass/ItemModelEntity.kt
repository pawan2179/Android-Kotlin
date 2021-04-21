package com.example.bill_slip_initial.EntityClass

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class ItemModelEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,

    @ColumnInfo(name = "cost")
    val cost : Double,

    @ColumnInfo(name = "thumbnail")
    val thumbnail : Bitmap
)