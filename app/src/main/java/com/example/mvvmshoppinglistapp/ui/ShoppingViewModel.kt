package com.example.mvvmshoppinglistapp.ui

import androidx.lifecycle.ViewModel
import com.example.mvvmshoppinglistapp.data.db.entites.ShoppingItem
import com.example.mvvmshoppinglistapp.data.repositories.ShoppingRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel() {

    fun upsert(item: ShoppingItem) =
        GlobalScope.launch {
            repository.upsert(item)
        }

    fun delete(item: ShoppingItem) = GlobalScope.launch {
        repository.delete(item)
    }

    fun getAllShoppingItems() = repository.getAllShoppingItems()

}