package com.example.cricbuzzassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.cricbuzzassignment.R
import com.example.cricbuzzassignment.model.menus.MenuItem
import com.example.cricbuzzassignment.model.restaurants.Restaurant

class RestaurantsMenuAdapter : RecyclerView.Adapter<BaseViewHolder<*>>(), Filterable {

    companion object {
        private const val RESTAURANT_TYPE = 1
        private const val MENU_TYPE = 0
    }

    private var data: ArrayList<Any>? = null
    private lateinit var originalData: ArrayList<Any>

    fun setData(data: ArrayList<Any>) {
        this.data = data
        originalData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            RESTAURANT_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_restaurant, parent, false)
                RestaurantViewHolder(view)
            }
            MENU_TYPE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
                MenuViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type...")
        }
    }

    override fun getItemCount(): Int {
       return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = data?.get(position)
        when (holder) {
            is RestaurantViewHolder -> holder.bind(item as Restaurant)
            is MenuViewHolder -> holder.bind(item as MenuItem)
            else -> throw IllegalArgumentException("Invalid view type...")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (data?.get(position)) {
            is Restaurant -> RESTAURANT_TYPE
            is MenuItem -> MENU_TYPE
            else -> throw IllegalArgumentException("Invalid type of data...")
        }
    }

    override fun getFilter(): Filter = filterResult

    private val filterResult = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val list = ArrayList<Any>()

            if (constraint == null || constraint.isEmpty()) {
                list.addAll(originalData)
            } else {
                val filterPattern = constraint.toString().trim()
                for (item in originalData) {

                }
            }

            val result = FilterResults()
            result.values = list
            return result
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            data?.clear()
            data?.addAll(results?.values as ArrayList<*>)
            notifyDataSetChanged()
        }
    }

}