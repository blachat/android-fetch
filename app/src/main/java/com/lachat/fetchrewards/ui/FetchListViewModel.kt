package com.lachat.fetchrewards.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lachat.fetchrewards.repository.Repository
import com.lachat.fetchrewards.model.FetchItem
import kotlinx.coroutines.launch

class FetchListViewModel : ViewModel() {

    private val _fetchList = MutableLiveData<List<FetchItem>>()
    val fetchList: LiveData<List<FetchItem>> get() = _fetchList
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    init {
        getFetchListItems()
    }

    private fun getFetchListItems() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val repository = Repository()
                val response = repository.getUsers().filter { !it.name.isNullOrEmpty() }
                val groupedAndSortedList = response
                    .groupBy { it.listId }
                    .toSortedMap(compareBy { it })
                    .flatMap { entry ->
                        entry.value.sortedWith(compareBy { it.id })
                    }
                _fetchList.value = groupedAndSortedList
                _loading.value = false
            } catch (e: Exception) {
                println("Error: ${e.message}")
                _loading.value = false
            }
        }
    }
}