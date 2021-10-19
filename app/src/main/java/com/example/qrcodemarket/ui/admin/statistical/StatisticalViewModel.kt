package com.example.qrcodemarket.ui.admin.statistical

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import com.example.qrcodemarket.data.model_new.Statistical
import com.example.qrcodemarket.data.respositories.StatisticalRepository
import com.example.qrcodemarket.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class StatisticalViewModel(
    private val repository: StatisticalRepository
) : ViewModel() {
    private lateinit var job:Job

    private val _statisticals = MutableLiveData<List<Statistical>>()
    val statisticals: LiveData<List<Statistical>>
        get() = _statisticals

     fun getStatistical(){
         viewModelScope.launch {
             val data = withContext(Dispatchers.IO) { repository.getStatistical() }
             _statisticals.value = data
         }
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}