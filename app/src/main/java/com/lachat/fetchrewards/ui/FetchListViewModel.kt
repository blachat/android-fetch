package com.lachat.fetchrewards.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lachat.fetchrewards.repository.Repository
import com.lachat.fetchrewards.model.FetchItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FetchListViewModel : ViewModel() {

    private val _fetchList = MutableLiveData<List<FetchItem>>()
    val fetchList: LiveData<List<FetchItem>> get() = _fetchList
    private val _groupList = MutableLiveData<List<Int>>()
    val groupList: LiveData<List<Int>> get() = _groupList
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading
    private val _networkError = MutableLiveData(false)
    val networkError: LiveData<Boolean> get() = _networkError
    private var originalData: List<FetchItem>? = null
    private val _filteredGroupIds = mutableListOf<Int>()
    val filteredGroupIds: List<Int> get() = _filteredGroupIds

    init {
        getFetchListItems()
    }

    fun getFetchListItems() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val repository = Repository()
                val groupedAndSortedList = withContext(Dispatchers.IO) {
                    val response = repository.getFetchItems().filter { !it.name.isNullOrEmpty() }
                    response
                        .groupBy { it.listId }
                        .toSortedMap(compareBy { it })
                        .flatMap { entry ->
                            entry.value.sortedWith(compareBy { it.id })
                        }
                }
                _fetchList.value = groupedAndSortedList
                originalData = groupedAndSortedList


                // Create Initial group for filter
                if (groupList.value == null) {
                    val sortedGroup = originalData!!.map { it.listId }
                        .distinct()
                        .sorted()
                    _filteredGroupIds.addAll(sortedGroup)
                    _groupList.value = sortedGroup
                }

                _loading.value = false
                _networkError.value = false
            } catch (e: Exception) {
                println("Error: ${e.message}")
                _loading.value = false
                _networkError.value = true
            }
        }
    }

    fun filterToSelectedGroups(filteredGroupIds: List<Int>) {
        _filteredGroupIds.clear()
        _filteredGroupIds.addAll(filteredGroupIds)
        viewModelScope.launch {
            _loading.value = true
            _fetchList.value = originalData?.filter { it.listId in _filteredGroupIds }
            _loading.value = false
        }
    }
}