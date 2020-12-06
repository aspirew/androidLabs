package com.rafal_behrendt.hadescatalogue

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BoonsListFragment() : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_boons_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val boons = arguments?.getParcelableArrayList<Boon>("boonsList")
        val color = arguments?.getString("color")
        initRecyclerView(boons, color)
    }

    private fun initRecyclerView(boons: ArrayList<Boon>?, color: String?) {

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.boonsRV)
        val adapter = BoonsRecyclerViewAdapter(boons, color, requireView().context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)

    }

    fun newInstance(boonsList: ArrayList<Boon>, color: String): Fragment? {
        val args = Bundle()
        args.putParcelableArrayList("boonsList", boonsList)
        args.putString("color", color)
        val f = BoonsListFragment()
        f.arguments = args
        return f
    }

}

