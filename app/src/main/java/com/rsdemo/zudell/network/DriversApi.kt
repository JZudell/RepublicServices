package com.rsdemo.zudell.network

import com.rsdemo.zudell.util.Constants
import retrofit2.Response
import retrofit2.http.GET


interface DriversApi {

    @GET(Constants.DATA_ENDPOINT)
    suspend fun getData(): Response<DriverAndRouteResponse>
}