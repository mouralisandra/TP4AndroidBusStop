package com.example.tp4.busClassFeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tp4.busschedule.dao.ScheduleDAO

class BusScheduleViewModelFactory(
    private val scheduleDao: ScheduleDAO
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BusScheduleViewModel::class.java))
        {
            @Suppress("UNCHECKED_CAST")
            return BusScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}