package com.rsdemo.zudell.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DriversScreen(
    state: DriversScreenState,
    onEvent: (DriversScreenEvent) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){

        items(state.drivers){ driver ->
            Text(
                text = "${driver.firstName} ${driver.lastName}   ID:${driver.id}",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onEvent(DriversScreenEvent.driverClicked(driver.id)) },
                fontSize = 20.sp,
                color = Color.Black
            )
        }

    }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        FloatingActionButton(modifier = Modifier.align(Alignment.TopEnd), onClick = {
            onEvent(DriversScreenEvent.alphabatizeListByLastname)
        }) {
            Icon(imageVector = Icons.Default.List, contentDescription = "Sort Drivers by Lastname")
        }
    }

}


