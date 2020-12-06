package com.rafal_behrendt.hadescatalogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2

class GalleryFragment() : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val images = arguments?.getStringArrayList("images")
        images?.let { initViewPager(view, it) }
    }

    private fun initViewPager(view: View, images: ArrayList<String>){
        val viewPager: ViewPager2 = view.findViewById(R.id.galleryViewPager)
        val adapter = GalleryViewPagerAdapter(childFragmentManager, lifecycle, images)
        viewPager.adapter = adapter
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
    }

    fun newInstance(images: ArrayList<String>): Fragment? {
        val args = Bundle()
        args.putStringArrayList("images", images)
        val f = GalleryFragment()
        f.arguments = args
        return f
    }

}