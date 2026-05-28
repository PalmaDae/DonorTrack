package com.example.feature_calendar.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.utils.DonationCalculator
import com.example.data.utils.UserDonationRecord
import com.example.domain.model.donation.DonationType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate

data class CalendarUiState(
    val lastDonation: UserDonationRecord? = null,
    val nextAvailableDates: Map<DonationType, LocalDate> = emptyMap()
)

class CalendarViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CalendarUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadCalendarData()
    }

    private fun loadCalendarData() {

        val mockHistory = listOf(
            UserDonationRecord(
                id = 1,
                date = LocalDate.of(2026, 5, 19),
                type = DonationType.BLOOD
            )
        )

        val nextDates = DonationCalculator.calculateNextAvailableDates(mockHistory)

        _uiState.update {
            it.copy(
                lastDonation = mockHistory.lastOrNull(),
                nextAvailableDates = nextDates
            )
        }
    }
}