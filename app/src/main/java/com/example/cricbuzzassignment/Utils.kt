package com.example.cricbuzzassignment

import android.content.Context
import android.util.Log
import com.example.cricbuzzassignment.model.menus.Category
import com.example.cricbuzzassignment.model.menus.MenuModel
import com.example.cricbuzzassignment.model.restaurants.Restaurant
import com.example.cricbuzzassignment.model.restaurants.RestaurantModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Utils {


    companion object {
        private val TAG = "Utils"

        fun getJsonDataFromAsset(context: Context, fileName: String): String? {
            val jsonString: String
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
                Log.e(TAG, "getJsonDataFromAsset: $jsonString")
            } catch (exception: Exception) {
                exception.printStackTrace()
                return null
            }
            return jsonString
        }

        fun getMappedData(restaurantsData: RestaurantModel, menusData: MenuModel):HashMap<Restaurant,ArrayList<Category>>{
            val map =
                HashMap<Restaurant, ArrayList<Category>>()
            for (restaurantItem in restaurantsData.restaurants) {
                map[restaurantItem] = ArrayList()
                for (menuItem in menusData.menus) {
                    if (menuItem.restaurantId == restaurantItem.id) {
                        map[restaurantItem] = menuItem.categories as ArrayList<Category>
                    }
                }
            }
            return map
        }

        fun getData(context:Context):HashMap<Restaurant,ArrayList<Category>>{
            val restaurants = getJsonDataFromAsset(context, "restaurants.json")
            val menus = getJsonDataFromAsset(context, "menus.json")

            val restaurantsType = object : TypeToken<RestaurantModel>() {}.type
            val menusType = object : TypeToken<MenuModel>() {}.type

            val gson = Gson()

            val restaurantsData: RestaurantModel = gson.fromJson(restaurants, restaurantsType)
            val menusData: MenuModel = gson.fromJson(menus, menusType)

            return getMappedData(restaurantsData,menusData)
        }
    }

}