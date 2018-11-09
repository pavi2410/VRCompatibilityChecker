package appinventor.ai_pavitragolchha.VR

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val myDataset: ArrayList<String>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val resultName = view.findViewById<TextView>(R.id.result_name)
        val resultValue = view.findViewById<TextView>(R.id.result_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.result_list_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, pos: Int) {
        val strings = holder.view.resources.getStringArray(R.array.result_names)
        holder.resultName.text = strings[pos]
        holder.resultValue.text = myDataset[pos]
    }

    override fun getItemCount() = myDataset.size
}