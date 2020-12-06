package com.rafal_behrendt.hadescatalogue

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment


class DescriptionFragment() : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val description = arguments?.getString("description")
        val image = arguments?.getString("image")
        val imageID = requireContext().resources.getIdentifier(image, "drawable", requireContext().packageName)
        val descTV = view.findViewById<TextView>(R.id.descriptionTV)
        descTV.text = description
        view.findViewById<ImageView>(R.id.descriptionImageView).setImageResource(imageID)
        descTV.movementMethod = ScrollingMovementMethod()
    }

    fun newInstance(description: String, image: String): Fragment? {
        val args = Bundle()
        args.putString("description", description)
        args.putString("image", image)
        val f = DescriptionFragment()
        f.arguments = args
        return f
    }
}