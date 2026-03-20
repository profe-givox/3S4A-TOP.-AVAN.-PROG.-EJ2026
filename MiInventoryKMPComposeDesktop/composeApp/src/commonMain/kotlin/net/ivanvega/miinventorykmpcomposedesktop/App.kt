package net.ivanvega.miinventorykmpcomposedesktop

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import net.ivanvega.miinventorykmpcomposedesktop.cache.DatabaseDriverFactory
import net.ivanvega.miinventorykmpcomposedesktop.cache.ItemDAO
import net.ivanvega.miinventorykmpcomposedesktop.ui.screens.AddItemScreen
import net.ivanvega.miinventorykmpcomposedesktop.ui.screens.ItemListScreen
import net.ivanvega.miinventorykmpcomposedesktop.ui.screens.ItemViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(dataBaseFactory: DatabaseDriverFactory) {
    val navController = rememberNavController()
    val dao = remember { ItemDAO(dataBaseFactory ) }
    val itemViewModel: ItemViewModel = viewModel{  ItemViewModel(dao) }

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Items") }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { navController.navigate("addItem")} ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Item")
                }
            }
        ) { paddingValues ->
            NavHost(navController = navController, startDestination = "itemList") {
                composable("itemList") {
                    ItemListScreen(
                        viewModel = itemViewModel,
                          Modifier.fillMaxSize().padding(paddingValues)

                    )
                }
                composable("addItem") {
                    AddItemScreen(
                        viewModel = itemViewModel,
                        onItemAdded = { navController.popBackStack()
                        },
                        Modifier.fillMaxSize().padding(paddingValues)
                    )
                }
            }
        }
    }
}