package com.example.tp4.busClassFeature

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tp4.busschedule.dao.ScheduleDAO
import com.example.tp4.busschedule.Entites.Schedule

class BusScheduleViewModel(private val scheduleDAO: ScheduleDAO): ViewModel() {
    fun fullSchedule(): LiveData<List<Schedule>> = scheduleDAO.getAll()

    fun scheduleForStopName(name: String): LiveData<List<Schedule>> = scheduleDAO.getByStopName(name)
}