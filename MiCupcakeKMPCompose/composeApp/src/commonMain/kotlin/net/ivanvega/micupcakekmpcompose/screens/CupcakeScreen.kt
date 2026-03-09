package net.ivanvega.micupcakekmpcompose.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import micupcakekmpcompose.composeapp.generated.resources.Res
import micupcakekmpcompose.composeapp.generated.resources.app_name
import micupcakekmpcompose.composeapp.generated.resources.back_button
import micupcakekmpcompose.composeapp.generated.resources.choose_flavor
import micupcakekmpcompose.composeapp.generated.resources.choose_pickup_date
import micupcakekmpcompose.composeapp.generated.resources.order_summary
import net.ivanvega.micupcakekmpcompose.data.DataSource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource


/**
 * enum values that represent the screens in the app
 */
enum class CupcakeScreen(val title: StringResource) {
    Start(title = Res.string.app_name),
    Flavor(title = Res.string.choose_flavor),
    Pickup(title = Res.string.choose_pickup_date),
    Summary(title = Res.string.order_summary)
}

/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeAppBar(
    currentScreen: CupcakeScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,

        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(Res.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun CupcakeApp(
    viewModel: OrderViewModel = viewModel{ OrderViewModel() },
    navController: NavHostController = rememberNavController()
) {
    // Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
    val currentScreen = CupcakeScreen.valueOf(
        backStackEntry?.destination?.route ?: CupcakeScreen.Start.name
    )
    Scaffold(
        topBar = {
            CupcakeAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = CupcakeScreen.Start.name,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {
            composable (route = CupcakeScreen.Start.name) {
                StartOrderScreen(
                    quantityOptions = DataSource.quantityOptions,
                    onNextButtonClicked = {
                        viewModel.setQuantity(it)
                        navController.navigate(CupcakeScreen.Flavor.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )

            }
            composable (route= CupcakeScreen.Flavor.name) {
                SelectOptionScreen(
                    subtotal = uiState.price,
                    options = DataSource.flavors.map{ stringResource (it)   },
                    onSelectionChanged =  {  p -> viewModel.setFlavor(p) },
                    onCancelButtonClicked = {},
                    onNextButtonClicked = {navController.navigate(route = CupcakeScreen.Pickup.name)}

                )
            }

            composable (route= CupcakeScreen.Pickup.name) {
                SelectOptionScreen(
                    subtotal = uiState.price,
                    options = uiState.pickupOptions,
                    onSelectionChanged = { p -> } ,
                    onCancelButtonClicked = {},
                    onNextButtonClicked = {navController.navigate(route = CupcakeScreen.Pickup.name)}

                )
            }
        }

    }


}
