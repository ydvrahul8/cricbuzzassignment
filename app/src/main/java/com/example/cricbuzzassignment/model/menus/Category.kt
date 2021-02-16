package com.example.cricbuzzassignment.model.menus

import com.google.gson.annotations.SerializedName

data class Category(
    val id: String,
    @SerializedName("menu-items")
    val menu_items: List<MenuItem>,
    val name: String
)