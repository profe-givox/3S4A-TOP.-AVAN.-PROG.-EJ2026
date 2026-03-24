package net.ivanvega.miinventorykmpcomposedesktop

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
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
import net.ivanvega.miinventorykmpcomposedesktop.ui.screens.HomeDestination
import net.ivanvega.miinventorykmpcomposedesktop.ui.screens.ItemEntryDestination
import net.ivanvega.miinventorykmpcomposedesktop.ui.screens.ItemListScreen
import net.ivanvega.miinventorykmpcomposedesktop.ui.screens.ItemViewModel
import org.jetbrains.compose.resources.StringResource

import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.unit.dp
import androidx.lifecycle.createSavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.savedstate.savedState
import net.ivanvega.miinventorykmpcomposedesktop.ui.screens.ItemDetailsDestination
import net.ivanvega.miinventorykmpcomposedesktop.ui.screens.ItemDetailsScreen
import net.ivanvega.miinventorykmpcomposedesktop.ui.screens.ItemDetailsViewModel
import net.ivanvega.miinventorykmpcomposedesktop.ui.screens.ItemEditDestination
import net.ivanvega.miinventorykmpcomposedesktop.ui.screens.ItemEditScreen
import net.ivanvega.miinventorykmpcomposedesktop.ui.screens.ItemEditViewModel
import org.jetbrains.compose.resources.stringResource


/**
 * Interface to describe the navigation destinations for the app
 */
interface NavigationDestination {
    /**
     * Unique name to define the path for a composable
     */
    val route: String

    /**
     * String resource id to that contains title to be displayed for the screen.
     */
    val titleRes: StringResource
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(dataBaseFactory: DatabaseDriverFactory, navController: NavHostController = rememberNavController()) {
    val dao = remember { ItemDAO(dataBaseFactory ) }
    val itemViewModel: ItemViewModel = viewModel{  ItemViewModel(dao) }

    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreenTitle = when (backStackEntry?.destination?.route) {
        ItemEntryDestination.route -> ItemEntryDestination.titleRes
        else -> HomeDestination.titleRes
    }
    val canNavigateBack = navController.previousBackStackEntry != null

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(currentScreenTitle))
                            },
                    navigationIcon = {
                        // La recomposición es activada por 'backStackEntry'.
                        // 'canNavigateBack' se recalculará cuando 'backStackEntry' cambie.
                        if (canNavigateBack) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier.padding(horizontal = 12.dp).clickable {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }

                )
            },
            floatingActionButton = {
                if (!canNavigateBack) {
                    FloatingActionButton(onClick = { navController.navigate(ItemEntryDestination.route) }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Item")
                    }
                }
            }
        ) { paddingValues ->
            NavHost(navController = navController, startDestination = HomeDestination.route) {
                composable(route = HomeDestination.route) {
                    ItemListScreen(
                        viewModel = itemViewModel,
                           onItemClick     = {
                               navController.navigate("${ItemDetailsDestination.route}/${it.id}")
                                             },
                        modifier =  Modifier.fillMaxSize().padding(paddingValues)

                    )
                }
                composable(route = ItemEntryDestination.route) {
                    AddItemScreen(
                        viewModel = itemViewModel,
                        onItemAdded = { navController.popBackStack()
                        },
                        Modifier.fillMaxSize().padding(paddingValues)
                    )
                }
                composable(
                    route = ItemDetailsDestination.routeWithArgs,
                    arguments = listOf(navArgument(ItemDetailsDestination.itemIdArg) {
                        type = NavType.IntType
                    })
                ) {
                    ItemDetailsScreen(
                        navigateToEditItem = { navController.navigate("${ItemEditDestination.route}/$it") },
                        navigateBack = { navController.navigateUp() },
                        modifier =  Modifier.fillMaxSize().padding(paddingValues),
                        innerPadding = paddingValues,
                        viewModel = viewModel { ItemDetailsViewModel(
                                                    savedStateHandle = this.createSavedStateHandle(),
                                                    itemsRepository = dao
                                                )
                        }
                    )
                }
                composable(
                    route = ItemEditDestination.routeWithArgs,
                    arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                        type = NavType.IntType
                    })
                ) {
                    ItemEditScreen(navigateBack = { navController.popBackStack() },
                        onNavigateUp = { navController.navigateUp() },
                        innerPadding = paddingValues,
                        viewModel = viewModel {
                                                    ItemEditViewModel(
                                                        savedStateHandle = this.createSavedStateHandle(),
                                                        itemsRepository = dao
                                                    )
                        }
                    )
                }
            }
        }
    }
}