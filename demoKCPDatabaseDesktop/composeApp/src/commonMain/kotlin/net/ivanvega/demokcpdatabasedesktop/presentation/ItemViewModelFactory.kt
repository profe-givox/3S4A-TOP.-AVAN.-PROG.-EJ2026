package net.ivanvega.demokcpdatabasedesktop.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.ivanvega.demokcpdatabasedesktop.data.ItemRepository

class ItemViewModelFactory(private val itemRepository: ItemRepository) : ViewModelProvider.Factory {

    fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(itemRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}