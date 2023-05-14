package com.rsdemo.zudell.datamanagement

import android.annotation.SuppressLint
import com.rsdemo.zudell.datamanagement.database.DriverDao
import com.rsdemo.zudell.datamanagement.database.Models
import com.rsdemo.zudell.datamanagement.database.RouteDao
import com.rsdemo.zudell.network.ApiClient
import kotlinx.coroutines.*

class DriverAndRouteRepository(
    private val driverDao: DriverDao,
    private val routeDao: RouteDao
) {
    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }

    @SuppressLint("CheckResult")
    suspend fun getDriversAndRoutes(){
        val apiClient = ApiClient()

        val drivers = ArrayList<Models.RoomDriver>()
        val routes = ArrayList<Models.RoomRoute>()
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            val data = apiClient.getData()
            data.body()?.drivers?.forEach {driver ->
                drivers.add(
                    Models.RoomDriver(
                        id = driver.id,
                        firstName = driver.name.split(" ").first(),
                        lastName = driver.name.split(" ").last())
                )
            }
            driverDao.insertAll(drivers)
            data.body()?.routes?.forEach { route ->
                routes.add(
                    Models.RoomRoute(
                        id = route.id,
                        type = route.type,
                        name = route.name
                    )
                )
            }
            routeDao.insertAll(routes)
        }

    }


}