package com.prabitha.acronymfinder.ui.acronyms.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prabitha.acronymfinder.databinding.AcronymsListItemBinding
import com.prabitha.acronymfinder.models.Acronyms
import com.prabitha.acronymfinder.models.LongForms
import com.prabitha.acronymfinder.ui.acronyms.AcronymItemViewState

class AcronymListAdapter(private val clickListener: (position: Int) -> Unit) :
    RecyclerView.Adapter<AcronymListAdapter.AcronymViewHolder>() {

    private var acronymItems: MutableList<Acronyms> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcronymViewHolder {
        val binding = AcronymsListItemBinding.inflate(LayoutInflater.from(parent.context))
        return AcronymViewHolder(binding, clickListener)
    }

    override fun getItemCount() = if (acronymItems.size != 0) acronymItems[0].lfs.size else 0
    override fun onBindViewHolder(holder: AcronymViewHolder, position: Int) {
        holder.bind(acronymItems[0].lfs[position])
    }

    fun setData(acronymItems: List<Acronyms>) {
        this.acronymItems.clear()
        this.acronymItems.addAll(acronymItems)
        notifyDataSetChanged()
    }

    inner class AcronymViewHolder(
        private val binding: AcronymsListItemBinding,
        private val clickListener: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(acronymItem: LongForms) {
            binding.item = AcronymItemViewState(acronymItem)
            binding.imageView.setOnClickListener {
                clickListener(adapterPosition)
            }
        }
    }

}