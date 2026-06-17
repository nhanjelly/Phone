package com.uilover.project276.activties

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.uilover.project276.adapter.FavoriteAdapter
import com.uilover.project276.databinding.ActivityExploreBinding
import com.uilover.project276.domain.ItemsModel

class ExploreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExploreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityExploreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Bấm nút back để quay lại
        binding.backBtn.setOnClickListener { finish() }

        // Gọi hàm tải dữ liệu
        loadAllDrinks()
    }

    private fun loadAllDrinks() {
        binding.progressBar.visibility = View.VISIBLE

        // Kết nối đến nút "Items" trên Firebase chứa tất cả món ăn/đồ uống
        val ref = FirebaseDatabase.getInstance().getReference("Items")

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<ItemsModel>()
                if (snapshot.exists()) {
                    for (issue in snapshot.children) {
                        val item = issue.getValue(ItemsModel::class.java)
                        if (item != null) {
                            list.add(item)
                        }
                    }
                }

                binding.progressBar.visibility = View.GONE

                if (list.isNotEmpty()) {
                    // Hiển thị lưới 2 cột mượt mà
                    binding.exploreRecyclerView.layoutManager = GridLayoutManager(this@ExploreActivity, 2)
                    // Tái sử dụng luôn FavoriteAdapter cực kỳ tiện lợi
                    binding.exploreRecyclerView.adapter = FavoriteAdapter(list)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                binding.progressBar.visibility = View.GONE
            }
        })
    }
}