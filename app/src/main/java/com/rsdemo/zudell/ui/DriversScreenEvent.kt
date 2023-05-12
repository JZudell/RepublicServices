package com.rsdemo.zudell.ui

sealed interface DriversScreenEvent {
    object alphabatizeListByLastname: DriversScreenEvent
    data class driverClicked(val id: String): DriversScreenEvent
}