package com.example.mvvmshoppinglistapp.data.db.entites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem(
        @ColumnInfo(name = "item_name")
        var name: String,
        @ColumnInfo(name = "item_category")
        var category: String,
        @ColumnInfo(name = "item_amount")
        var amount: Int
) {
    @PrimaryKey(autoGenerate = true)
    var Id: Int? = null
}
