package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var wishList: ArrayList<Wish>
    private lateinit var adapter: WishListAdapter
    private lateinit var btnSubmit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wishList = ArrayList()
        recyclerView = findViewById(R.id.rvWishes)
        btnSubmit = findViewById(R.id.btnSubmit)
        adapter = WishListAdapter(this,wishList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        val etName = findViewById<EditText>(R.id.etName)
        val etPrice = findViewById<EditText>(R.id.etPrice)
        val etLink = findViewById<EditText>(R.id.etLink)
        btnSubmit.setOnClickListener {
            val name = etName.text.toString()
            val priceString = etPrice.text.toString()

            if (name.isEmpty()) {
                Toast.makeText(this, "Invalid item name!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val price = priceString.toDouble()
                val link = etLink.text.toString()

                wishList.add(Wish("$name", "$price", "$link"))
                adapter.notifyDataSetChanged()
                recyclerView.smoothScrollToPosition(adapter.itemCount - 1)

                etLink.setText("")
                etPrice.setText("")
                etName.setText("")
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Invalid price!", Toast.LENGTH_SHORT).show()
            }
        }


    }
}