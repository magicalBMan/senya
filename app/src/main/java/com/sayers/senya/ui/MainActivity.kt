package com.sayers.senya.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.sayers.senya.R
import com.sayers.senya.data.Attraction
import com.sayers.senya.data.AttractionsResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    val attractionsList: List<Attraction> by lazy {
        parseAttractions()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
    }

    private fun parseAttractions(): List<Attraction> {
        val textFromFile =
            resources.openRawResource(R.raw.croatia).bufferedReader().use { it.readText() }

        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        val adapter: JsonAdapter<AttractionsResponse> =
            moshi.adapter(AttractionsResponse::class.java)
        return adapter.fromJson(textFromFile)!!.attractions
    }
}