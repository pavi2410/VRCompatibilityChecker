package appinventor.ai_pavitragolchha.VR

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DetailsListAdapter(private val items: ArrayList<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int = items[position].itemType.ordinal

    class CheckViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val value: ImageView = view.findViewById(R.id.value)
    }

    class TextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val value: TextView = view.findViewById(R.id.value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (ItemType.values()[viewType]) {
            ItemType.CHECK -> CheckViewHolder(inflater.inflate(R.layout.check_view_item, parent, false))
            ItemType.TEXT -> TextViewHolder(inflater.inflate(R.layout.text_view_item, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        when (holder) {
            is CheckViewHolder -> {
                holder.name.text = items[pos].name
                holder.value.setImageResource(if (items[pos].value as Boolean) R.drawable.check else R.drawable.cross)
            }
            is TextViewHolder -> {
                holder.name.text = items[pos].name
                holder.value.text = items[pos].value as String
            }
        }
    }

    override fun getItemCount() = items.size
}

