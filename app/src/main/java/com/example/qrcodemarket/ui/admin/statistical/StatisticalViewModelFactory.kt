package com.example.qrcodemarket.ui.admin.statistical

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.qrcodemarket.data.respositories.StatisticalRepository

@Suppress("UNCHECKED_CAST")
class StatisticalViewModelFactory(
    private val repository:StatisticalRepository
):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StatisticalViewModel(repository) as T
    }
}