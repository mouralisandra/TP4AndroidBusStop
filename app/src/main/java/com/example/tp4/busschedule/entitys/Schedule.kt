package com.example.tp4.busschedule.Entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity
data class Schedule(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "stop_name") val stopName : String,
    @ColumnInfo(name = "arrival_time") val arrivalTime : Int
)
