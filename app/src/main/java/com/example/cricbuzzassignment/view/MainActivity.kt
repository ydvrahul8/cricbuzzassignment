package com.example.cricbuzzassignment.view

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.cricbuzzassignment.R
import com.example.cricbuzzassignment.Utils.Companion.getData
import com.example.cricbuzzassignment.Utils.Companion.getJsonDataFromAsset
import com.example.cricbuzzassignment.Utils.Companion.getMappedData
import com.example.cricbuzzassignment.adapter.RestaurantsMenuAdapter
import com.example.cricbuzzassignment.model.menus.Category
import com.example.cricbuzzassignment.model.menus.MenuModel
import com.example.cricbuzzassignment.model.restaurants.Restaurant
import com.example.cricbuzzassignment.model.restaurants.RestaurantModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchResult( "Chicken")

        val adapter = RestaurantsMenuAdapter()

        recyclerView.adapter = adapter
    }

    private fun searchResult(s: String) {

        val map = getData(applicationContext)

        val result = HashSet<Restaurant>()

        for ((key, values) in map) {
            Log.e(TAG, "searchResult: key is $key and value is $values")
            if (key.name.contains(s, true) || key.cuisine_type.contains(s, true))
                result.add(key)
            for (menuTitle in values) {
                if (menuTitle.name.contains(s, true))
                    result.add(key)
                for (menu in menuTitle.menu_items) {
                    if (menu.name.contains(s, true))
                        result.add(key)
                }
            }
        }

        Log.e(TAG, "searchResult: final result is $result")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return true
    }

}