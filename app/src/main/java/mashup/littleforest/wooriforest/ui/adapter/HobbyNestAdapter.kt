package mashup.littleforest.wooriforest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import mashup.littleforest.wooriforest.R
import mashup.littleforest.wooriforest.data.model.response.LinkTransItem
import mashup.littleforest.wooriforest.databinding.ItemHobbyNestBinding

class HobbyNestAdapter
    : RecyclerView.Adapter<HobbyNestAdapter.HobbyNestViewModel>() {

    private val items = mutableListOf<LinkTransItem>()

    private var preSelectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbyNestViewModel {
        return HobbyNestViewModel(parent).apply {
            itemView.setOnClickListener {
                if (preSelectedPosition >= 0) {
                    items[preSelectedPosition] = items[preSelectedPosition].copy(isSelected = false)
                    items[adapterPosition] = items[adapterPosition].copy(isSelected = true)

                    notifyItemChanged(preSelectedPosition)
                    notifyItemChanged(adapterPosition)

                    preSelectedPosition = adapterPosition
                } else {
                    items[adapterPosition] = items[adapterPosition].copy(isSelected = true)
                    notifyItemChanged(adapterPosition)

                    preSelectedPosition = adapterPosition
                }
            }
        }
    }

    override fun onBindViewHolder(holder: HobbyNestViewModel, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun replaceAll(items: List<LinkTransItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun getSelectedItem() = items.find { it.isSelected }

    fun getSelectedItemId() = items.find { it.isSelected }?.id

    fun getSelectedItemTitle() = items.find { it.isSelected }?.title

    fun getShareTitle(): String {
        val shareTitle = StringBuilder()
        shareTitle.append("소비 내역을 통해 본 나의 관심 취미는\n")

        items.forEach {
            shareTitle.append("${it.title} ")
        }

        shareTitle.append("입니다~!")

        return shareTitle.toString()
    }

    class HobbyNestViewModel(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hobby_nest, parent, false)
    ) {

        private val binding: ItemHobbyNestBinding? = DataBindingUtil.bind(itemView)

        fun bind(item: LinkTransItem) {
            binding?.model = item
        }
    }
}