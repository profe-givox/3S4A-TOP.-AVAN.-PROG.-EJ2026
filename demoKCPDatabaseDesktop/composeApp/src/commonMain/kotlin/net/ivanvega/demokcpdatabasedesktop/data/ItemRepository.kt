package net.ivanvega.demokcpdatabasedesktop.data

import cache.Item

interface ItemRepository {
    fun getAllItems(): List<Item>
    fun getItemById(id: Long): Item?
    fun insertItem(name: String, price: Double, quantity: Long)
    fun updateItem(id: Long, name: String, price: Double, quantity: Long)
    fun deleteItem(id: Long)
}