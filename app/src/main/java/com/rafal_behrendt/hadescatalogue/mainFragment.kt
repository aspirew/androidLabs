package com.rafal_behrendt.hadescatalogue

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class mainFragment : Fragment() {

    var godsList: MutableList<God> = mutableListOf()
    private lateinit var viewModel: MainFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        godsList.addAll(viewModel.getGods())
        serveListVisibility()
        setHasOptionsMenu(true);

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.filtration_menu, menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.all ->{
                godsList.clear()
                godsList.addAll(viewModel.getGods())
                serveListVisibility()
                //updateList() <- fajnie jakby działało
                initRecyclerView() // działa ale niefajne
                true
            }
            R.id.favourites -> {
                godsList.clear()
                godsList.addAll(viewModel.getFavourites())
                serveListVisibility()
                //updateList() <- fajnie jakby działało
                initRecyclerView() // działa ale niefajne
                true
            }
            R.id.offensive -> {
                godsList.clear()
                godsList.addAll(viewModel.getGods().filter { god -> god.categories.contains("offensive") } as MutableList<God>)
                serveListVisibility()
                //updateList() <- fajnie jakby działało
                initRecyclerView() // działa ale niefajne
                true
            }
            R.id.defensive -> {
                godsList.clear()
                godsList.addAll( viewModel.getGods().filter { god -> god.categories.contains("defensive") } as MutableList<God>)
                serveListVisibility()
                //updateList() <- fajnie jakby działało
                initRecyclerView() // działa ale niefajne
                true
            }
            R.id.support -> {
                godsList.clear()
                godsList.addAll(viewModel.getGods().filter { god -> god.categories.contains("supportive") } as MutableList<God>)
                serveListVisibility()
                //updateList() //<- fajnie jakby działało
                initRecyclerView() // działa ale to jest niefajne
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun serveListVisibility(){
        if(godsList.isEmpty()) {
            view?.findViewById<ImageView>(R.id.mainFragmentimageView)?.visibility = View.VISIBLE
            view?.findViewById<TextView>(R.id.mainFragmenttextView)?.visibility = View.VISIBLE
        }
        else{
            view?.findViewById<ImageView>(R.id.mainFragmentimageView)?.visibility = View.INVISIBLE
            view?.findViewById<TextView>(R.id.mainFragmenttextView)?.visibility = View.INVISIBLE
        }
    }

    private fun updateList(){
        val recyclerView = requireView().findViewById<RecyclerView>(R.id.RVGods)
        requireActivity().runOnUiThread {
            recyclerView.adapter!!.notifyDataSetChanged()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView(){

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.RVGods)
        val adapter = RecyclerViewAdapter(godsList, viewModel, requireView().context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)

        val itemTouchHelper =  ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.removeFromFavourites(godsList[viewHolder.adapterPosition])
                viewModel.deleteGod(godsList[viewHolder.adapterPosition])
                godsList.removeAt(viewHolder.adapterPosition)
                adapter.notifyDataSetChanged()
            }

        })
       requireActivity().runOnUiThread {
            itemTouchHelper.attachToRecyclerView(recyclerView)
        }
    }
}