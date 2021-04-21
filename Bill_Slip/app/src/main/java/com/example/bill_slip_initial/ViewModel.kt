package com.example.bill_slip_initial

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bill_slip_initial.EntityClass.ItemModelEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : ItemRepository
    val allItems : LiveData<List<ItemModelEntity>>
    val itemCount : LiveData<Int>
    val totalCost : LiveData<Double>
    /*val itemCount: Int
    val totalCost : Double*/

    init {
        val dao = DatabaseClass.getDatabase(application)?.getDao()
        repository = ItemRepository(dao!!)
        allItems = repository.allItems
        itemCount = repository.itemCount
        totalCost = repository.totalCost
    }
    fun delete() = viewModelScope.launch(Dispatchers.IO) {
        repository.delete()
    }
    fun insertItem(entity: ItemModelEntity) = viewModelScope.launch (Dispatchers.IO){
        repository.insert(entity)
    }

}