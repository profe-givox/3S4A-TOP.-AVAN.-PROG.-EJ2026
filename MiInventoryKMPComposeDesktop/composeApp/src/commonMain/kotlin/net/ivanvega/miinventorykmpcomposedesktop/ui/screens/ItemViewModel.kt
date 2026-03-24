package net.ivanvega.miinventorykmpcomposedesktop.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cache.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.ivanvega.miinventorykmpcomposedesktop.cache.ItemDAO
import java.text.NumberFormat

class ItemViewModel (val dao: ItemDAO) : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items = _items.asStateFlow()
    init {
        loadItems()
    }
    private fun loadItems() {
        viewModelScope.launch(Dispatchers.IO) {
            _items.value = dao.getAllItems()
        }
    }
    fun insertItem(name: String, price: Double, quantity: Long) {
        viewModelScope.launch {
            dao.insertItem(name, price, quantity)
            loadItems()
        }
    }


    fun insertItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertItem(item.name, item.price, item.quantity)
        }
    }

}


/**
 * Represents Ui State for an Item.
 */
data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false
)

data class ItemDetails(
    val id: Long = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)

/**
 * Extension function to convert [ItemUiState] to [Item]. If the value of [ItemDetails.price] is
 * not a valid [Double], then the price will be set to 0.0. Similarly if the value of
 * [ItemUiState] is not a valid [Int], then the quantity will be set to 0
 */
fun ItemDetails.toItem(): Item = Item(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toLongOrNull() ?: 0
)

fun Item.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}

/**
 * Extension function to convert [Item] to [ItemUiState]
 */
fun Item.toItemUiState(isEntryValid: Boolean = false): ItemUiState = ItemUiState(
    itemDetails = this.toItemDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Item] to [ItemDetails]
 */
fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString()
)
