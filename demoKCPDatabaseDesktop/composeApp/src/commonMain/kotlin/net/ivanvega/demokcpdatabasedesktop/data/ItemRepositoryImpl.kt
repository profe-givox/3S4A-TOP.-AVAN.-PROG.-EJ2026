package net.ivanvega.demokcpdatabasedesktop.data

import cache.Item
import net.ivanvega.demokcpdatabasedesktop.cache.AppDatabase
import net.ivanvega.demokcpdatabasedesktop.cache.DatabaseDriverFactory

class ItemRepositoryImpl(databaseDriverFactory: DatabaseDriverFactory) : ItemRepository {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    override fun getAllItems(): List<Item> {
        return dbQuery.getAllItems().executeAsList()
    }

    override fun getItemById(id: Long): Item? {
        return dbQuery.getItemById(id).executeAsOneOrNull()
    }

    override fun insertItem(name: String, price: Double, quantity: Long) {
        dbQuery.insertItem(name, price, quantity)
    }

    override fun updateItem(id: Long, name: String, price: Double, quantity: Long) {
        dbQuery.updateItem(name, price, quantity, id)
    }

    override fun deleteItem(id: Long) {
        dbQuery.deleteItem(id)
    }
}