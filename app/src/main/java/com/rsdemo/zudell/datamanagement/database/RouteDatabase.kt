package com.rsdemo.zudell.datamanagement.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Models.RoomRoute::class],
    version = 1
)
abstract class RouteDatabase: RoomDatabase() {

    abstract val dao: RouteDao
}