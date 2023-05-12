package com.rsdemo.zudell.datamanagement.database

import androidx.room.Entity
import androidx.room.PrimaryKey

class Models {

    @Entity(tableName = "driverTable")
    data class RoomDriver(@PrimaryKey val id: String, val firstName: String, val lastName: String)

    @Entity(tableName = "routeTable")
    data class RoomRoute(@PrimaryKey val id: String, val type: String, val name: String)
}