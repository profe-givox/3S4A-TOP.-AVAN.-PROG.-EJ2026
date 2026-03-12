package net.ivanvega.demokcpdatabasedesktop.data

import app.cash.sqldelight.db.SqlDriver

interface DriverFactory {
    fun createDriver(): SqlDriver
}