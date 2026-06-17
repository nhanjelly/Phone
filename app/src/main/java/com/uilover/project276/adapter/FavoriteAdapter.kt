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

class FavoriteAdapter(private val items: ArrayList<ItemsModel>) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private lateinit var context: Context

    class ViewHolder(val binding: ViewholderItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val binding = ViewholderItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        // dữ liệu lên giao diện
        holder.binding.titleTxt.text = item.title
        holder.binding.priceTxt.text = "$" + item.price
       // holder.binding.ratingTxt.text = item.rating.toString()

        // Nạp ảnh bằng Glide
        Glide.with(context)
            .load(item.picUrl[0])
            .into(holder.binding.pic)

        // Bấm vào item bất kỳ sẽ mở trang chi tiết món đó
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("object", item)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}