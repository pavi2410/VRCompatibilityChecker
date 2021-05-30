package me.pavi2410.vrcc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.pavi2410.vrcc.databinding.ItemCheckViewBinding

class DetailsListAdapter(private val items: List<Item>) : RecyclerView.Adapter<DetailsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(ItemCheckViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    class ViewHolder(private val binding: ItemCheckViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) = with(binding) {
            messageIcon.setImageResource(item.icon)
            txtName.text = itemView.context.getString(item.name)
            imgResult.setImageResource(if (item.result) R.drawable.check else R.drawable.cross)
        }
    }
}