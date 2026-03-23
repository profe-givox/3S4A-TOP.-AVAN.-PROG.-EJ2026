package net.ivanvega.miinventorykmpcomposedesktop.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import miinventorykmpcomposedesktop.composeapp.generated.resources.Res
import miinventorykmpcomposedesktop.composeapp.generated.resources.app_name
import net.ivanvega.miinventorykmpcomposedesktop.NavigationDestination
import androidx.compose.ui.Modifier


object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = Res.string.app_name
}

@Composable
fun ItemListScreen(viewModel: ItemViewModel, modifier: Modifier) {
    val producots by viewModel.items.collectAsState(initial = emptyList())
    LazyColumn(modifier= modifier) {
        items(producots){
            producot ->
                Text(text = producot.name)
        }
    }

}