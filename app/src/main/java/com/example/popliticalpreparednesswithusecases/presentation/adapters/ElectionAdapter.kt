package com.example.popliticalpreparednesswithusecases.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.popliticalpreparednesswithusecases.data.model.Election
import com.example.popliticalpreparednesswithusecases.databinding.ElectionListItemBinding

class ElectionAdapter : RecyclerView.Adapter<ElectionAdapter.ElectionViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Election>() {

        override fun areItemsTheSame(oldItem: Election, newItem: Election): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Election, newItem: Election): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectionViewHolder {
        val binding = ElectionListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ElectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ElectionViewHolder, position: Int) {
        val election = differ.currentList[position]
        holder.bind(election)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ElectionViewHolder(val binding: ElectionListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(election: Election) {
            binding.electionNameTextView.text = election.name
            binding.electionDayTextView.text = election.electionDay
        }
    }

}