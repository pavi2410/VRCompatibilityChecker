package tk.pavi2410.vrcc

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_check_view.view.*

class DetailsListAdapter(private val items: List<Item>) : RecyclerView.Adapter<DetailsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.item_check_view))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Item) = with(itemView) {
            img_icon.setImageResource(item.icon)
            txt_name.text = context.getString(item.name)
            val drawable = if (item.result) R.drawable.check else R.drawable.cross
            img_result.setImageResource(drawable)
        }
    }
}