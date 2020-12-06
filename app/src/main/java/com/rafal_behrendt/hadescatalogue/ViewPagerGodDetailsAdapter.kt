package com.rafal_behrendt.hadescatalogue

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class ViewPagerGodDetailsAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, godDetails: God) : FragmentStateAdapter(fragmentManager, lifecycle) {

    var fragments : ArrayList<Fragment?> = arrayListOf(
            DescriptionFragment().newInstance(godDetails.desc, godDetails.mainImage),
            BoonsListFragment().newInstance(godDetails.boons, godDetails.color),
            GalleryFragment().newInstance(godDetails.gallery)
    )


    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]!!
    }

}