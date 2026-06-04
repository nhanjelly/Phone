package com.uilover.project276.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uilover.project276.activties.DetailActivity
import com.uilover.project276.databinding.ViewholderItemBinding
import com.uilover.project276.domain.ItemsModel

class ItemsAdapter(val items: MutableList<ItemsModel>) :
    RecyclerView.Adapter<ItemsAdapter.Viewholder>() {
    lateinit var context: Context

    class Viewholder(val binding: ViewholderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemsAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: ItemsAdapter.Viewholder, position: Int) {
        holder.binding.titleTxt.text = items[position].title
        holder.binding.priceTxt.text = "$" + items[position].price.toString()
        holder.binding.subtitleTxt.text = items[position].extra

        Glide.with(context)
            .load(items[position].picUrl[0])
            .into(holder.binding.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", items[position])
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = items.size
}