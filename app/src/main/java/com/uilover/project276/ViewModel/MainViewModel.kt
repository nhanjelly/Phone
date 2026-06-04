package com.uilover.project276.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.uilover.project276.domain.BannerModel
import com.uilover.project276.domain.CategoryModel
import com.uilover.project276.domain.ItemsModel
import com.uilover.project276.repository.MainRepository

class MainViewModel : ViewModel() {

    private val repository = MainRepository()

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }

    fun loadBanner(): LiveData<MutableList<BannerModel>> {
        return repository.loadBanner()
    }

    fun loadPopular(): LiveData<MutableList<ItemsModel>> {
        return repository.loadPopular()
    }

    fun loadItems(categoryId: String): LiveData<MutableList<ItemsModel>>{
        return repository.loadItemCategory(categoryId)
    }
}