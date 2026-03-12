package net.ivanvega.demokcpdatabasedesktop.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cache.Item
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.ivanvega.demokcpdatabasedesktop.data.ItemRepository

class ItemViewModel(private val itemRepository: ItemRepository) : ViewModel() {

    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items = _items.asStateFlow()

    init {
        loadItems()
    }

    fun loadItems() {
        viewModelScope.launch {
            _items.value = itemRepository.getAllItems()
        }
    }

    fun insertItem(name: String, price: Double, quantity: Long) {
        viewModelScope.launch {
            itemRepository.insertItem(name, price, quantity)
            loadItems()
        }
    }

    fun updateItem(id: Long, name: String, price: Double, quantity: Long) {
        viewModelScope.launch {
            itemRepository.updateItem(id, name, price, quantity)
            loadItems()
        }
    }

    fun deleteItem(id: Long) {
        viewModelScope.launch {
            itemRepository.deleteItem(id)
            loadItems()
        }
    }
}