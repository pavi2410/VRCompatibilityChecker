package appinventor.ai_pavitragolchha.VR

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_check_view.view.*

class DetailsListAdapter(private val items: List<Item>) : RecyclerView.Adapter<DetailsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.item_check_view))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(items[position])

    override fun getItemCount() = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Item) = with(itemView) {
            name.text = item.name
            val drawable = if (item.value) R.drawable.check else R.drawable.cross
            value.setImageResource(drawable)
        }
    }
}