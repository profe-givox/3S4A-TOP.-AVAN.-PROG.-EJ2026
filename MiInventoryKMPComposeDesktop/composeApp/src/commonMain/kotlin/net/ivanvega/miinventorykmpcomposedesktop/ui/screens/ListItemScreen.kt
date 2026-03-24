package net.ivanvega.miinventorykmpcomposedesktop.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import miinventorykmpcomposedesktop.composeapp.generated.resources.Res
import miinventorykmpcomposedesktop.composeapp.generated.resources.app_name
import net.ivanvega.miinventorykmpcomposedesktop.NavigationDestination
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cache.Item
import miinventorykmpcomposedesktop.composeapp.generated.resources.in_stock
import org.jetbrains.compose.resources.stringResource


object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = Res.string.app_name
}

@Composable
fun ItemListScreen(viewModel: ItemViewModel,
                    onItemClick: (Item) -> Unit,
                   modifier: Modifier) {
    val itemList by viewModel.items.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = modifier,
    ) {
        items(items = itemList, key = { it.id }) { item ->
            InventoryItem(item = item,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable { onItemClick(item) })
        }
    }

}

@Composable
private fun InventoryItem(
    item: Item, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = item.price.toString(),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = stringResource(Res.string.in_stock, item.quantity),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}