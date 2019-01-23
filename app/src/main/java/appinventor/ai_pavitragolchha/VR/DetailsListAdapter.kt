package appinventor.ai_pavitragolchha.VR

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DetailsListAdapter(private val items: ArrayList<Item>) : RecyclerView.Adapter<DetailsListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val value: ImageView = view.findViewById(R.id.value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsListAdapter.ViewHolder
            = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.check_view_item, parent, false))

    override fun onBindViewHolder(holder: DetailsListAdapter.ViewHolder, position: Int) {
        holder.name.text = items[position].name
        holder.value.setImageResource(if (items[position].value) R.drawable.check else R.drawable.cross)
    }

    override fun getItemCount() = items.size
}