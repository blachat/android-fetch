package com.lachat.fetchrewards.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.lachat.fetchrewards.R
import com.lachat.fetchrewards.databinding.ItemFetchBinding
import com.lachat.fetchrewards.model.FetchItem
import com.lachat.fetchrewards.utils.FetchUtils

class FetchItemsAdapter(private val items: List<FetchItem>) :
    RecyclerView.Adapter<FetchItemsAdapter.FetchItemViewHolder>() {

    inner class FetchItemViewHolder(private val binding: ItemFetchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FetchItem) {

            val cardBackgroundColor = FetchUtils.getCardColor(itemView.context, item.listId)

            binding.cardView.setCardBackgroundColor(cardBackgroundColor)
            binding.tvGroup.text = itemView.context.getString(R.string.group, item.listId)
            binding.tvItem.text = item.name?.replace(
                itemView.context.getString(R.string.item_field_name),
                itemView.context.getString(R.string.formatted_item_field_name)
            )
            binding.tvId.text = itemView.context.getString(R.string.item_id, item.id)
            binding.root.setOnClickListener {
                val action = FetchListFragmentDirections.actionListFragmentToDetailsFragment(item)
                findNavController(it).navigate(action)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FetchItemViewHolder {
        val binding = ItemFetchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FetchItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FetchItemViewHolder, position: Int) {
        holder.bind(items[position])
    }
}