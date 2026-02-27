package net.ivanvega.micupcakekmpcompose.data

import androidx.compose.ui.graphics.Path
import micupcakekmpcompose.composeapp.generated.resources.Res
import micupcakekmpcompose.composeapp.generated.resources.chocolate
import micupcakekmpcompose.composeapp.generated.resources.coffee
import micupcakekmpcompose.composeapp.generated.resources.one_cupcake
import micupcakekmpcompose.composeapp.generated.resources.red_velvet
import micupcakekmpcompose.composeapp.generated.resources.salted_caramel
import micupcakekmpcompose.composeapp.generated.resources.six_cupcakes
import micupcakekmpcompose.composeapp.generated.resources.twelve_cupcakes
import micupcakekmpcompose.composeapp.generated.resources.vanilla

object DataSource {
    val flavors = listOf(
        Res.string.vanilla,
        Res.string.red_velvet,
        Res.string.chocolate,
        Res.string.salted_caramel,
        Res.string.coffee
    )

    val quantityOptions = listOf(
        Pair(Res.string.one_cupcake, 1),
        Pair(Res.string.six_cupcakes, 6),
        Pair(Res.string.twelve_cupcakes, 12)
    )
}
