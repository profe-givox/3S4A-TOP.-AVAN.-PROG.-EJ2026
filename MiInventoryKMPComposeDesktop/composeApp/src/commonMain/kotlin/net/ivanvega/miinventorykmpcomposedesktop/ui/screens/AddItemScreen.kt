package net.ivanvega.miinventorykmpcomposedesktop.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import miinventorykmpcomposedesktop.composeapp.generated.resources.Res
import miinventorykmpcomposedesktop.composeapp.generated.resources.item_entry_title
import net.ivanvega.miinventorykmpcomposedesktop.NavigationDestination

object ItemEntryDestination : NavigationDestination {
    override val route = "item_entry"
    override val titleRes = Res.string.item_entry_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemScreen(viewModel: ItemViewModel, onItemAdded: () -> Unit, modifier: Modifier) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }


        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                label = { Text("Quantity") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    viewModel.insertItem(
                        name = name,
                        price = price.toDoubleOrNull() ?: 0.0,
                        quantity = quantity.toLongOrNull() ?: 0L
                    )
                    onItemAdded()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add")
            }
        }

}