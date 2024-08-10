package com.example.suitmediatestapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmediatestapp.data.DataItem
import com.example.suitmediatestapp.data.UserRepository

class MainViewModel(private val repository: UserRepository): ViewModel(){
    fun getusers() = repository.getUsers()

    val user: LiveData<PagingData<DataItem>> =
        repository.getuser().cachedIn(viewModelScope)
}