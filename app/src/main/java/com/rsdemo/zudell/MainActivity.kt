@file:Suppress("UNCHECKED_CAST", "DEPRECATION")

package com.rsdemo.zudell

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.rsdemo.zudell.datamanagement.database.DriverDatabase
import com.rsdemo.zudell.datamanagement.database.RouteDatabase
import com.rsdemo.zudell.ui.DriversScreen
import com.rsdemo.zudell.ui.DriversScreenViewModel
import com.rsdemo.zudell.ui.RouteScreen
import com.rsdemo.zudell.ui.theme.RepublicServicesTheme

class MainActivity : ComponentActivity() {


    //Shortcut DB initialization for sake of excersize.
    private val driverDb by lazy {
        Room.databaseBuilder(
            applicationContext,
            DriverDatabase::class.java,
            "drivers.db"
        ).build()
    }
    private val routeDb by lazy {
        Room.databaseBuilder(
            applicationContext,
            RouteDatabase::class.java,
            "routes.db"
        ).build()
    }

    private val viewModel by viewModels<DriversScreenViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return DriversScreenViewModel(driverDb.dao,routeDb.dao) as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RepublicServicesTheme {

                val state by viewModel.state.collectAsState()
                if (state.driverClicked.isNotBlank()){
                    RouteScreen(state = state)
                } else {
                    DriversScreen(state = state, onEvent = viewModel::onEvent)
                }

            }
        }
    }


    //short cut onBackPressed for ease of testing excersize
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {

        if (viewModel.state.value.driverClicked.isNotBlank()){
            viewModel.goBackToDriverScreen()
        }else{
            super.onBackPressed()
        }

    }
}
