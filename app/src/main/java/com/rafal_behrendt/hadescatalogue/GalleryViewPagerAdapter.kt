package com.rafal_behrendt.hadescatalogue

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class GalleryViewPagerAdapter (fragmentManager: FragmentManager, lifecycle: Lifecycle, val images: List<String>) : FragmentStateAdapter(fragmentManager, lifecycle) {

    var imageFragments : ArrayList<Fragment?> = arrayListOf()

    init{
        for (image in images){
            addImageFragment(image)
        }
    }

    private fun addImageFragment(image: String){
        imageFragments.add(GalleryItem().newInstance(image))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun createFragment(position: Int): Fragment {
        return imageFragments[position]!!
    }

}