package com.rafal_behrendt.hadescatalogue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class GalleryItem(): Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gallery_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image = arguments?.getString("image")
        val imageID = requireContext().resources.getIdentifier(image, "drawable", requireContext().packageName)
        view.findViewById<ImageView>(R.id.galleryItemImageView).setImageResource(imageID)
        view.findViewById<TextView>(R.id.textViewGalleryItem).text = ((image?.get(0)?.toUpperCase())!! + image!!.substring(1)).replace('_', ' ')

    }

    fun newInstance(image: String): Fragment? {
        val args = Bundle()
        args.putString("image", image)
        val f = GalleryItem()
        f.arguments = args
        return f
    }

}