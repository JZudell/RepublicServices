package com.rsdemo.zudell.datamanagement.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DriverDao {

    @Query("SELECT * FROM driverTable")
    fun getDrivers(): Flow<List<Models.RoomDriver>>

    @Query("SELECT * FROM driverTable ORDER BY lastName ASC")
    fun getDriversAlphabatizeLastname(): Flow<List<Models.RoomDriver>>

    @Upsert
    suspend fun insertAll(RoomDrivers: List<Models.RoomDriver>)

    @Query("DELETE FROM driverTable")
    fun deleteAll()

}