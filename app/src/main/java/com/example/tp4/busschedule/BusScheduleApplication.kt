package com.example.tp4.busschedule

import android.app.Application

class BusScheduleApplication : Application() {
    val database: AppDatabase by lazy{
        AppDatabase.getDatabase(this);
    }
}