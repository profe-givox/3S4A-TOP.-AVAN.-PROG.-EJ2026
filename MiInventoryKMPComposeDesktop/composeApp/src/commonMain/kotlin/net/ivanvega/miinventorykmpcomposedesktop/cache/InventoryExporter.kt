package net.ivanvega.miinventorykmpcomposedesktop.cache

import cache.Item

expect class InventoryExporter {
    suspend fun exportCsv(data: List<InventoryExportRow>)
    suspend fun exportPdf(data: List<Item>)
}