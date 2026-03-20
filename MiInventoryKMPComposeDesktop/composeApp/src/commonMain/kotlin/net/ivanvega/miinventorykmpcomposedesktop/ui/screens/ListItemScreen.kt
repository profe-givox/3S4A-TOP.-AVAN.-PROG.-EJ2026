package net.ivanvega.miinventorykmpcomposedesktop.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


@Composable
fun ItemListScreen(viewModel: ItemViewModel, modifier: androidx.compose.ui.Modifier) {
    val producots by viewModel.items.collectAsState(initial = emptyList())
    LazyColumn(modifier= modifier) {
        items(producots){
            producot ->
                Text(text = producot.name)
        }
    }

}