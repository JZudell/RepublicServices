package com.rsdemo.zudell.ui

import com.rsdemo.zudell.datamanagement.database.Models

data class DriversScreenState(
    val drivers: List<Models.RoomDriver> = emptyList(),
    val routes: List<Models.RoomRoute> = emptyList(),
    val driverClicked: String = ""

)