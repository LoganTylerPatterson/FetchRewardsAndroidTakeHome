package com.android.fetchandroidloganp.ui.screens.FetchRewardsItemScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.android.fetchandroidloganp.model.CollapsableItem
import com.android.fetchandroidloganp.model.Item
import com.android.fetchandroidloganp.repository.ItemRepository
import com.android.fetchandroidloganp.api.APIResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ItemsViewModel(
    private val itemRepository: ItemRepository
) : ViewModel() {

    private val _collapsableItems =
        MutableStateFlow<APIResult<List<CollapsableItem>>>(APIResult.Loading())

    val collapsableItems: StateFlow<APIResult<List<CollapsableItem>>> =
        _collapsableItems.asStateFlow()

    init {
        getAllItems()
    }

    private fun fromItemListToCollapsableListItem(itemList: List<Item>): List<CollapsableItem> {
        return itemList
            .filter { !it.name.isNullOrBlank() }
            .groupBy { it.listId }
            .mapValues { (_, value) -> value.sortedBy { it.name } }
            .toSortedMap()
            .map { (listId, items) ->
                CollapsableItem(
                    listId = listId,
                    itemsList = items
                )
            }
    }

    private fun getAllItems() {
        viewModelScope.launch {
            try {
                _collapsableItems.value = APIResult.Loading() // Show loading state
                val result = itemRepository.getAllItems() // Get the result (no Flow)

                when (result) {
                    is APIResult.Success -> {
                        val items = result.data!!
                        val collapsableListItems = fromItemListToCollapsableListItem(items)
                        _collapsableItems.value = APIResult.Success(collapsableListItems)
                    }
                    is APIResult.Error -> {
                        _collapsableItems.value = APIResult.Error(result.message ?: "An error occurred")
                    }

                    else -> {}
                }
            } catch (e: Exception) {
                _collapsableItems.value = APIResult.Error("An error occurred")
            }
        }
    }

    companion object {
        fun provideFactory(repository: ItemRepository): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(ItemsViewModel::class.java)) {
                        return ItemsViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }
}
