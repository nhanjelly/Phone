package com.uilover.project276.adapter

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.uilover.project276.R
import com.uilover.project276.activties.ItemListActivity
import com.uilover.project276.databinding.ViewholderCategoryBinding
import com.uilover.project276.domain.CategoryModel

class CategoryAdapter(val items: MutableList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.Viewholder>() {
    private lateinit var context: Context
    private var selectedPosition = -1
    private var lastSelectedPosition = -1

    class Viewholder(val binding: ViewholderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderCategoryBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
        val item = items[position]

        holder.binding.titleCat.text = item.title

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)

            Handler(Looper.getMainLooper()).postDelayed({

                val intent= Intent(context, ItemListActivity::class.java).apply {
                    putExtra("id",item.id.toString())
                    putExtra("title",item.title)
                }
                ContextCompat.startActivity(context,intent,null)
            }, 500)
        }

        if (selectedPosition == position) {
            holder.binding.titleCat.setBackgroundResource(R.drawable.brown_full_corner)
            holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.white))
        } else {
            holder.binding.titleCat.setBackgroundResource(R.drawable.cream_full_corner)
            holder.binding.titleCat.setTextColor(context.resources.getColor(R.color.darkBrown))
        }
    }

    override fun getItemCount(): Int = items.size
}