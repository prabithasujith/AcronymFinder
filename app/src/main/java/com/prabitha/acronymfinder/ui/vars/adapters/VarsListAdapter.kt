package com.prabitha.acronymfinder.ui.vars.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prabitha.acronymfinder.databinding.VarsListItemBinding
import com.prabitha.acronymfinder.models.Vars
import com.prabitha.acronymfinder.ui.vars.VarsItemViewState

class VarsListAdapter : RecyclerView.Adapter<VarsListAdapter.VarsViewHolder> (){

    private val varsItems = mutableListOf<Vars>()
    inner class VarsViewHolder(private val view: VarsListItemBinding) : RecyclerView.ViewHolder(view.root){
        fun bind(vars: Vars) {
            view.item = VarsItemViewState(vars)
        }
    }

    fun setData(items: List<Vars>){
        varsItems.addAll(items)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VarsViewHolder {
        val binding =  VarsListItemBinding.inflate(LayoutInflater.from(parent.context))
        return VarsViewHolder(binding)
    }

    override fun getItemCount() = varsItems.size

    override fun onBindViewHolder(holder: VarsViewHolder, position: Int) {
        holder.bind(varsItems[position])
    }

}