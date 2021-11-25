package com.example.popliticalpreparednesswithusecases.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.popliticalpreparednesswithusecases.data.model.Office
import com.example.popliticalpreparednesswithusecases.databinding.RepresentativeListItemBinding

class RepresentativeAdapter :
    RecyclerView.Adapter<RepresentativeAdapter.RepresentativeViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Office>() {

        override fun areItemsTheSame(
            oldItem: Office,
            newItem: Office
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: Office,
            newItem: Office
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepresentativeViewHolder {
        val binding = RepresentativeListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return RepresentativeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepresentativeViewHolder, position: Int) {
        val representative = differ.currentList[position]
        holder.bind(representative)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class RepresentativeViewHolder(val binding: RepresentativeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(representative: Office) {
            binding.apply {
                office.text = representative.name
//                officialName.text = representative.official.name
//                officialParty.text = representative.official.party
            }

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(representative)
                }
            }
        }

    }

    private var onItemClickListener: ((Office) -> Unit)? = null

    fun setOnItemClickListener(listener: (Office) -> Unit) {
        onItemClickListener = listener
    }

}