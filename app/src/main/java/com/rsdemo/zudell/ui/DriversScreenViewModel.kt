package com.rsdemo.zudell.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rsdemo.zudell.datamanagement.DriverAndRouteRepository
import com.rsdemo.zudell.datamanagement.database.DriverDao
import com.rsdemo.zudell.datamanagement.database.Models
import com.rsdemo.zudell.datamanagement.database.RouteDao
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class DriversScreenViewModel(
    private val driverDao: DriverDao,
    private val routeDao: RouteDao
): ViewModel() {

    init {
        viewModelScope.launch {
            DriverAndRouteRepository(driverDao,routeDao).getDriversAndRoutes()
        }
    }

    private val _sortByLastName = MutableStateFlow(false)
    @OptIn(ExperimentalCoroutinesApi::class)
    private val _drivers = _sortByLastName
        .flatMapLatest { sortByLastName ->
            if (sortByLastName){
                driverDao.getDriversAlphabatizeLastname()
            } else {
                driverDao.getDrivers()
            }

        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _driverClicked = MutableStateFlow("")

    private val _driverRoutes = MutableStateFlow(DriversScreenState().routes)

    private fun getRoutes(driverId: String){

        viewModelScope.launch {
            val listOfRoutes = ArrayList<Models.RoomRoute>()
            val routes = routeDao.getRoutes()

            routes.firstOrNull { it.id == driverId }?.let { route -> listOfRoutes.add(route) }
            if (driverId.toInt() % 2 == 0){
                routes.firstOrNull { it.type == "R" }?.let { route -> listOfRoutes.add(route) }
            }
            if (driverId.toInt() % 5 == 0){
                routes.filter { it.type == "C" }.elementAt(1).let { route -> listOfRoutes.add(route) }
            }
            if (listOfRoutes.isEmpty()){
                routes.last { it.type == "I" }.let { route -> listOfRoutes.add(route) }
            }

            _driverRoutes.value  = listOfRoutes

        }


    }

    fun goBackToDriverScreen(){
        _driverClicked.value = ""
    }

    private val _state = MutableStateFlow(DriversScreenState())
    val state = combine(_state, _drivers, _driverRoutes,_driverClicked) {state, drivers, driverRoutes, driverClicked ->
        state.copy(
            drivers = drivers,
            routes = driverRoutes,
            driverClicked = driverClicked
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DriversScreenState())

    fun onEvent(event: DriversScreenEvent) {
        when(event) {
            DriversScreenEvent.alphabatizeListByLastname -> _sortByLastName.value = true
            is DriversScreenEvent.driverClicked -> {
                getRoutes(event.id)
                _driverClicked.value = event.id
            }
        }
    }
}