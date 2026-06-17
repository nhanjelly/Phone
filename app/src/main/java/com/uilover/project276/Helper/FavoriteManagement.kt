package com.uilover.project276.Helper

import android.content.Context
import android.content.SharedPreferences
import com.uilover.project276.domain.ItemsModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavoriteManagement(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("FavoritePrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    // Lấy danh sách yêu thích
    fun getListFavorites(): ArrayList<ItemsModel> {
        val json = prefs.getString("FavoriteList", null)
        val type = object : TypeToken<ArrayList<ItemsModel>>() {}.type
        return if (json != null) {
            gson.fromJson(json, type)
        } else {
            arrayListOf() // Đã sửa thành công sang cú pháp chuẩn Kotlin để hết lỗi đỏ
        }
    }

    // Thêm hoặc Xóa khỏi danh sách yêu thích
    fun toggleFavorite(item: ItemsModel): Boolean {
        val listFavorites = getListFavorites()
        var isFavorite = false

        // Kiểm tra xem món này đã có trong danh sách chưa dựa vào thuộc tính title
        val existingItem = listFavorites.find { it.title == item.title }

        if (existingItem != null) {
            listFavorites.remove(existingItem) // Nếu có rồi thì xóa (Bỏ yêu thích)
        } else {
            listFavorites.add(item) // Nếu chưa có thì thêm vào (Yêu thích)
            isFavorite = true
        }

        // Lưu lại vào bộ nhớ máy
        val json = gson.toJson(listFavorites)
        prefs.edit().putString("FavoriteList", json).apply()

        return isFavorite // Trả về true nếu vừa thêm tim, false nếu vừa bỏ tim
    }

    // Kiểm tra trạng thái hiện tại (để hiển thị icon tim đỏ hay trắng)
    fun isFavorite(item: ItemsModel): Boolean {
        val listFavorites = getListFavorites()
        return listFavorites.any { it.title == item.title }
    }
}