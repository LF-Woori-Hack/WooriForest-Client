package mashup.littleforest.wooriforest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.databinding.ItemHobbyNestBinding

class HobbyNestAdapter
    : RecyclerView.Adapter<HobbyNestAdapter.HobbyNestViewModel>() {

    private val items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbyNestViewModel {
        return HobbyNestViewModel(parent)
    }

    override fun onBindViewHolder(holder: HobbyNestViewModel, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun replaceAll(items: List<String>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class HobbyNestViewModel(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hobby_nest, parent, false)
    ) {

        private val binding: ItemHobbyNestBinding? = DataBindingUtil.bind(itemView)

        fun bind(item: String) {
            //binding?.item = item
        }
    }
}