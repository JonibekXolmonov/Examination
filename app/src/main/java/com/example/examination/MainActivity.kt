package com.example.examination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examination.adapter.RestAdapter
import com.example.examination.model.Restaurant
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var restaurants: ArrayList<Restaurant>
    private lateinit var restAdapter: RestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.rv_main)
        //layout manager is set in .xml
        restaurants = addRestaurants()

        refreshAdapter()
    }

    private fun refreshAdapter() {
        restAdapter = RestAdapter(restaurants)
        recyclerView.adapter = restAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
                val totalItemCount = layoutManager.itemCount
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                val endHasBeenReached = lastVisible + 5 >= totalItemCount

                if (totalItemCount > 0 && endHasBeenReached) {
                    Snackbar.make(recyclerView,"End",Snackbar.LENGTH_SHORT).show()



                    restAdapter.addTeachers(addRestaurants())
                }
            }
        })
    }

    private fun addRestaurants(): java.util.ArrayList<Restaurant> {
        return ArrayList<Restaurant>().apply {
            for (i in 0..10) {
                this.add(
                    Restaurant(
                        randomImage(),
                        randomName(),
                        randomPrice(),
                        randomRating().toFloat(),
                        randomCity(),
                        "Uzbekistan"
                    )
                )
            }
        }
    }

    private fun randomName(): String {
        val names = arrayListOf(
            "Fast Food",
            "Best Plov",
            "Osh Center",
            "KFC",
            "Dinner House",
            "Quick Breakfast"
        )

        return names[Random.nextInt(0, 6)]
    }

    private fun randomImage(): Int {
        val images = arrayListOf(
            R.drawable.rest_1,
            R.drawable.rest_2,
            R.drawable.rest_3,
            R.drawable.rest_4,
            R.drawable.rest_5,
        )

        return images[Random.nextInt(0, 5)]
    }


    private fun randomCity(): String {
        val cities =
            arrayListOf("Tashkent", "Bukhara", "Navai", "Kashkhadarya", "Sirdarya", "Fergana")

        return cities[Random.nextInt(0, 6)]
    }

    private fun randomPrice(): Int {
        return Random.nextInt(30, 200)
    }

    private fun randomRating(): Int {
        return Random.nextInt(2, 5)
    }

}