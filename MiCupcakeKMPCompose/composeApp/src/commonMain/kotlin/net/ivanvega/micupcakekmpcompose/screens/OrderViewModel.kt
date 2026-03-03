package net.ivanvega.micupcakekmpcompose.screens

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import net.ivanvega.micupcakekmpcompose.data.DataSource
import net.ivanvega.micupcakekmpcompose.data.OrderUiState
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class OrderViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        OrderUiState(pickupOptions = pickupOptions()))

    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()



    /**
     * Returns a list of date options starting with the current date and the following 3 dates.
     */
    @OptIn(ExperimentalTime::class)
    private fun pickupOptions(): List<String> {
        val dateOptions = mutableListOf<String>()
        val now = Clock.System.now()
        val timeZone = TimeZone.currentSystemDefault()
        // add current date and the following 3 dates.
        repeat(4) {
            val day = now.plus(it, DateTimeUnit.DAY, timeZone)
            dateOptions.add(day.toLocalDateTime(timeZone).date.toString())
        }
        return dateOptions
    }
}