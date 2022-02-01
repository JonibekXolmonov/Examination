package com.example.examination.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examination.R
import com.example.examination.model.Restaurant
import org.w3c.dom.Text

class RestAdapter(private val restaurants: ArrayList<Restaurant>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        (holder as ViewHolder).apply {
            img_rest.setImageResource(restaurant.image)
            tv_name.text = restaurant.name
            tv_price.text = "$${restaurant.price}"
            tv_city.text = restaurant.city
            tv_country.text = restaurant.country
            rb_main.rating = restaurant.rating
        }
    }

    override fun getItemCount(): Int = restaurants.size

    @SuppressLint("NotifyDataSetChanged")
    fun addTeachers(extraRestaurants: ArrayList<Restaurant>) {
        restaurants.addAll(extraRestaurants)
        notifyDataSetChanged()
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val img_rest: ImageView = view.findViewById(R.id.img_rest)
    val tv_name: TextView = view.findViewById(R.id.tv_name)
    val tv_price: TextView = view.findViewById(R.id.tv_price)
    val tv_city: TextView = view.findViewById(R.id.tv_city)
    val tv_country: TextView = view.findViewById(R.id.tv_country)
    val rb_main: RatingBar = view.findViewById(R.id.rb_main)
}
