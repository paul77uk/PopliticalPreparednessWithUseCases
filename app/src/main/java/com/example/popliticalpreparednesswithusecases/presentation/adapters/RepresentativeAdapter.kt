package com.example.popliticalpreparednesswithusecases.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.popliticalpreparednesswithusecases.R
import com.example.popliticalpreparednesswithusecases.data.model.Representative
import com.example.popliticalpreparednesswithusecases.databinding.RepresentativeListItemBinding

class RepresentativeAdapter :
    RecyclerView.Adapter<RepresentativeAdapter.RepresentativeViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Representative>() {

        override fun areItemsTheSame(
            oldItem: Representative,
            newItem: Representative
        ): Boolean {
            return oldItem.office == newItem.office
        }

        override fun areContentsTheSame(
            oldItem: Representative,
            newItem: Representative
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

        fun bind(representative: Representative) {
            binding.apply {
                office.text = representative.office.name
                officialName.text = representative.official.name
                officialParty.text = representative.official.party

                if (representative.official.photoUrl != null) {
                    Glide.with(image.context).
                    load(representative.official.photoUrl)
                        .placeholder(R.drawable.ic_profile)
                        .circleCrop()
                        .into(image)
                } else image.setImageResource(R.drawable.ic_profile)
            }

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(representative)
                }
            }
        }

    }

    private var onItemClickListener: ((Representative) -> Unit)? = null

    fun setOnItemClickListener(listener: (Representative) -> Unit) {
        onItemClickListener = listener
    }

}