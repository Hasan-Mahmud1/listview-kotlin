package com.example.listkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val listView: ListView = findViewById(R.id.listView)

        val listItems: Array<String> = arrayOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as String
            // Do something with the selected item
            Toast.makeText(this, "Selected item: $selectedItem", Toast.LENGTH_SHORT).show()


            val intent = Intent(this, DetailsActivity2::class.java)
            intent.putExtra("itemName", selectedItem)
            startActivity(intent)
            finish()

        }

        val searchView:SearchView = findViewById(R.id.searchView)

        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if (listItems.contains(p0)){
                    adapter.filter.filter(p0)
                }else{
                    Toast.makeText(this@MainActivity, "No Match Found", Toast.LENGTH_SHORT).show()
                }
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return true
            }

        })


    }
}