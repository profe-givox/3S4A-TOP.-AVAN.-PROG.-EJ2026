package net.ivanvega.demokcpdatabasedesktop

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.ivanvega.demokcpdatabasedesktop.cache.DatabaseDriverFactory
import net.ivanvega.demokcpdatabasedesktop.data.DriverFactory
import net.ivanvega.demokcpdatabasedesktop.data.ItemRepositoryImpl
import net.ivanvega.demokcpdatabasedesktop.presentation.AddItemScreen
import net.ivanvega.demokcpdatabasedesktop.presentation.ItemListScreen
import net.ivanvega.demokcpdatabasedesktop.presentation.ItemViewModel
import net.ivanvega.demokcpdatabasedesktop.presentation.ItemViewModelFactory

@Composable
fun App(driverFactory: DatabaseDriverFactory) {
    val navController = rememberNavController()
    val itemRepository = remember { ItemRepositoryImpl(driverFactory) }
    val itemViewModel: ItemViewModel = viewModel{  ItemViewModel(itemRepository) }

    MaterialTheme {
        NavHost(navController = navController, startDestination = "itemList") {
            composable("itemList") {
                ItemListScreen(
                    viewModel = itemViewModel,
                    onAddItemClick = { navController.navigate("addItem") }
                )
            }
            composable("addItem") {
                AddItemScreen(
                    viewModel = itemViewModel,
                    onItemAdded = { navController.popBackStack() }
                )
            }
        }
    }
}