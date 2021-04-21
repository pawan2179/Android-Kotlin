package com.example.bill_slip_initial

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bill_slip_initial.DaoClass.DaoClass
import com.example.bill_slip_initial.EntityClass.ItemModelEntity

@Database(entities = [ItemModelEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DatabaseClass : RoomDatabase() {

    abstract fun getDao() : DaoClass?

    companion object {
        @Volatile
        private var INSTANCE : DatabaseClass ?= null

        fun getDatabase(context: Context) : DatabaseClass? {
            /*return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseClass::class.java,
                    "DATABASE"
                ).build()
                INSTANCE = instance
                instance
            }*/
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<DatabaseClass>(
                    context.applicationContext, DatabaseClass::class.java, "database"
                ).build()
            }
            return INSTANCE
        }
    }
}