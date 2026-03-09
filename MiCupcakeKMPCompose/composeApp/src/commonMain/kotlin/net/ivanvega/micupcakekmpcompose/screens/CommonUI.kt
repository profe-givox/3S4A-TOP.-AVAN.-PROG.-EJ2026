package net.ivanvega.micupcakekmpcompose.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import micupcakekmpcompose.composeapp.generated.resources.Res
import micupcakekmpcompose.composeapp.generated.resources.subtotal_price
import org.jetbrains.compose.resources.stringResource

/**
 * Composable that displays formatted [price] that will be formatted and displayed on screen
 */
@Composable
fun FormattedPriceLabel(subtotal: String, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(Res.string.subtotal_price, subtotal),
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall
    )
}
