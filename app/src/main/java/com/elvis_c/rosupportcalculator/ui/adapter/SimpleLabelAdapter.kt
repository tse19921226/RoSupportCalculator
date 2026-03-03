package com.elvis_c.rosupportcalculator.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elvis_c.rosupportcalculator.databinding.ItemSimpleLabelBinding

class SimpleLabelAdapter : ListAdapter<String, SimpleLabelAdapter.SimpleLabelViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleLabelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSimpleLabelBinding.inflate(inflater, parent, false)
        return SimpleLabelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimpleLabelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SimpleLabelViewHolder(
        private val binding: ItemSimpleLabelBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(text: String) {
            binding.tvLabel.text = text
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}
