package net.ivanvega.demokcpdatabasedesktop.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import net.ivanvega.demokcpdatabasedesktop.cache.AppDatabase

class JvmDriverFactory : DriverFactory {
    override fun createDriver(): SqlDriver {
        return JdbcSqliteDriver("jdbc:sqlite:app.db")
    }
}