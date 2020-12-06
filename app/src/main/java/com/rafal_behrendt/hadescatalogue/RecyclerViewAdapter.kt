package com.rafal_behrendt.hadescatalogue
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(
        val godsList: List<God>,
        val model: MainFragmentViewModel,
        val context: Context
) : RecyclerView.Adapter<RecyclerViewAdapter.godsViewHolder>() {

    class godsViewHolder(itemView: View, val model: MainFragmentViewModel, var god: God? = null) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val mainImage = itemView.findViewById<ImageView>(R.id.godImage)
        val name = itemView.findViewById<TextView>(R.id.godName)
        val favouriteBtn = itemView.findViewById<ImageView>(R.id.favouriteBtn)

        init {
            itemView.setOnClickListener {
                god?.let {
                    val directions = mainFragmentDirections.actionMainFragmentToGodDetails(it)
                    itemView.findNavController().navigate(directions)
                }
            }
            favouriteBtn.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val fav = model.getFavourites()
            if(fav.contains(god)) {
                favouriteBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                god?.let { model.removeFromFavourites(it) }
            }
            else {
                favouriteBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
                god?.let { model.addToFavourites(it) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): godsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.god_element, parent, false)
        return godsViewHolder(view, model)
    }

    override fun onBindViewHolder(holder: godsViewHolder, position: Int) {

        val imageID = context.resources.getIdentifier(godsList[position].mainImage, "drawable", context.packageName)
        holder.mainImage.setImageResource(imageID)
        holder.name.text = godsList[position].name
        holder.god = godsList[position]

        if(model.getFavourites().contains(godsList[position]))
            holder.favouriteBtn.setImageResource(R.drawable.ic_baseline_favorite_24)
        else
            holder.favouriteBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24)

    }

    override fun getItemCount(): Int {
        return godsList.size
    }

}