package net.ivanvega.demokcpdatabasedesktop.cache

import app.cash.sqldelight.db.SqlDriver
import cache.Item

interface DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    internal fun getAllItems() : List<Item> {
        return dbQuery.getAllItems().executeAsList()
    }

    internal fun insertItem(name: String, price: Double, quantity: Long) {
        dbQuery.insertItem(name, price, quantity )
    }

    internal fun updateItem(id: Long, name: String, price: Double, quantity: Long) {
        dbQuery.updateItem(name, price, quantity, id)
    }

    internal fun deleteItem(id: Long) {
        dbQuery.deleteItem(id)
    }

    internal fun getItemById(id: Long): Item? {
        return dbQuery.getItemById(id).executeAsOneOrNull()
    }

}