package com.rsdemo.zudell.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RouteScreen(
    state: DriversScreenState,
    // onEvent would be used here for a back button if the requirements for this screen asked for a back button
){

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){

        items(state.routes) { route ->
            Text(text = "Type:${route.type} Name:${route.name}", modifier = Modifier.padding(16.dp))
        }
    }

}