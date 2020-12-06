package com.rafal_behrendt.hadescatalogue
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BoonsRecyclerViewAdapter(
    val boonsList: ArrayList<Boon>?,
    val color: String?,
    val context: Context
) : RecyclerView.Adapter<BoonsRecyclerViewAdapter.boonsViewHolder>() {

    class boonsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.boonImageView)
        val name = itemView.findViewById<TextView>(R.id.boonNameTV)
        val desc = itemView.findViewById<TextView>(R.id.boonDescriptionTV)
        val value = itemView.findViewById<TextView>(R.id.boonValue)
        val divider = itemView.findViewById<View>(R.id.divider)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): boonsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.boon_item, parent, false)
        return boonsViewHolder(view)
    }

    override fun onBindViewHolder(holder: boonsViewHolder, position: Int) {
        val imageID = context.resources.getIdentifier(boonsList?.get(position)?.image, "drawable", context.packageName)
        holder.image.setImageResource(imageID)
        holder.name.text = boonsList?.get(position)?.name
        holder.desc.text = boonsList?.get(position)?.desc
        holder.value.text = boonsList?.get(position)?.value
        holder.divider.setBackgroundColor(Color.parseColor(color))
    }

    override fun getItemCount(): Int {
        return boonsList?.size ?: 0
    }


}