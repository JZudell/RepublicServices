package com.rsdemo.zudell.datamanagement.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Models.RoomDriver::class],
    version = 1
)
abstract class DriverDatabase: RoomDatabase() {

    abstract val dao: DriverDao
}