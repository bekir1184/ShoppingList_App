package com.example.mvvmshoppinglistapp.ui.dialog

import com.example.mvvmshoppinglistapp.data.db.entites.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}