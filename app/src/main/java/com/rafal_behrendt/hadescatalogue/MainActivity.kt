package com.rafal_behrendt.hadescatalogue

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.parcel.Parcelize

@Parcelize
data class God(val name: String, val mainImage: String, val gallery: ArrayList<String>, val color: String,
               val categories: List<String>, val desc: String, val boons: ArrayList<Boon>) : Parcelable

@Parcelize
data class Boon(val name: String, val desc: String, val value: String, val image: String) : Parcelable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    /*
    TODO: gallery <dynamic>
    TODO: add more data
    TODO: how about live data?
    TODO: removing favourite causes some shit
     */



}