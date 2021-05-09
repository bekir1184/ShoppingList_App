package com.example.mvvmshoppinglistapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmshoppinglistapp.R
import com.example.mvvmshoppinglistapp.data.db.ShoppingDatabase
import com.example.mvvmshoppinglistapp.data.db.entites.ShoppingItem
import com.example.mvvmshoppinglistapp.data.repositories.ShoppingRepository
import com.example.mvvmshoppinglistapp.ui.adapter.ShoppingItemAdapter
import com.example.mvvmshoppinglistapp.ui.dialog.AddDialogListener
import com.example.mvvmshoppinglistapp.ui.dialog.AddShoppingItemDialog
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment :Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database  = ShoppingDatabase(requireContext())
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProviders.of(this, factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)

        rvShoppingItems.layoutManager = LinearLayoutManager(context)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this.viewLifecycleOwner, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(
                requireContext(),
                object : AddDialogListener {
                    override fun onAddButtonClicked(item: ShoppingItem) {
                        viewModel.upsert(item)
                    }
                }).show()
        }
    }
}