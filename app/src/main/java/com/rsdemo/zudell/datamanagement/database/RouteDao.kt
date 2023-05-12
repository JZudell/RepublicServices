package com.rsdemo.zudell.datamanagement.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface RouteDao {

    @Query("SELECT * FROM routeTable")
    suspend fun getRoutes(): List<Models.RoomRoute>

    @Upsert
    suspend fun insertAll(RoomRoutes: List<Models.RoomRoute>)

    @Query("DELETE FROM routeTable")
    fun deleteAll()

}
