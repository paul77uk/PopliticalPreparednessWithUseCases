package com.example.popliticalpreparednesswithusecases.presentation.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.popliticalpreparednesswithusecases.R
import com.example.popliticalpreparednesswithusecases.data.model.Channel
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
                representative.official.channels.let {
                    if (it != null) {
                        showSocialLinks(it)
                    }
                }
                representative.official.urls.let { showWWWLinks(it) }

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

        private fun showSocialLinks(channels: List<Channel>) {
            val facebookUrl = getFacebookUrl(channels)
            if (!facebookUrl.isNullOrBlank()) { enableLink(binding.facebookIcon, facebookUrl) }

            val twitterUrl = getTwitterUrl(channels)
            if (!twitterUrl.isNullOrBlank()) { enableLink(binding.twitterIcon, twitterUrl) }
        }

        private fun showWWWLinks(urls: List<String>) {
            enableLink(binding.wwwIcon, urls.first())
        }

        private fun getFacebookUrl(channels: List<Channel>): String? {
            return channels.filter { channel -> channel.type == "Facebook" }
                .map { channel -> "https://www.facebook.com/${channel.id}" }
                .firstOrNull()
        }

        private fun getTwitterUrl(channels: List<Channel>): String? {
            return channels.filter { channel -> channel.type == "Twitter" }
                .map { channel -> "https://www.twitter.com/${channel.id}" }
                .firstOrNull()
        }

        private fun enableLink(view: ImageView, url: String) {
            view.visibility = View.VISIBLE
            view.setOnClickListener { setIntent(url) }
        }

        private fun setIntent(url: String) {
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            itemView.context.startActivity(intent)
        }


    }

    private var onItemClickListener: ((Representative) -> Unit)? = null

    fun setOnItemClickListener(listener: (Representative) -> Unit) {
        onItemClickListener = listener
    }

}