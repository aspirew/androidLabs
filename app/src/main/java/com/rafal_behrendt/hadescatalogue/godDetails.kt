package com.rafal_behrendt.hadescatalogue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class godDetails : Fragment() {

    private val args: godDetailsArgs by navArgs()
    private lateinit var god: God

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_god_details, container, false)

        god = args.godDetails

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.godNameTV).text = god.name
        initViewPager(view)
    }

    private fun initViewPager(view: View){
        val viewPager: ViewPager2 = view.findViewById(R.id.pager)
        val adapter = ViewPagerGodDetailsAdapter(childFragmentManager, lifecycle, god)
        viewPager.adapter = adapter

        val tabLayout : TabLayout = view.findViewById(R.id.tablayout)
        var name: List<String> = listOf("Description", "Boons", "Gallery")
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = name[position]
        }.attach()
    }
}