package com.uilover.project276.activties

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.uilover.project276.Helper.FavoriteManagement
import com.uilover.project276.adapter.FavoriteAdapter
import com.uilover.project276.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var favoriteManagement: FavoriteManagement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteManagement = FavoriteManagement(this)

        // Sự kiện nhấn nút Back để thoát về màn hình trước đó
        binding.backBtn.setOnClickListener { finish() }

        displayFavorites()
    }

    override fun onResume() {
        super.onResume()
        // Mỗi khi quay lại màn hình này, danh sách sẽ tự làm mới (nếu lỡ bỏ thích ở màn Detail)
        displayFavorites()
    }

    private fun displayFavorites() {
        val list = favoriteManagement.getListFavorites()

        if (list.isEmpty()) {
            binding.emptyTxt.visibility = View.VISIBLE
            binding.favoriteRecyclerView.visibility = View.GONE
        } else {
            binding.emptyTxt.visibility = View.GONE
            binding.favoriteRecyclerView.visibility = View.VISIBLE

            // Thiết kế hiển thị dạng lưới 2 cột
            binding.favoriteRecyclerView.layoutManager = GridLayoutManager(this, 2)
            binding.favoriteRecyclerView.adapter = FavoriteAdapter(list)
        }
    }
}