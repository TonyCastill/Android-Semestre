package com.ita.myapp.classes.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

//import java.lang.reflect.Modifier


@Composable
fun Components(navController: NavController) {
    var component by remember{ mutableStateOf("") } //Can assign a value
    // A reactive component to UI COMPONENTS
    // A global variable that its state can by updated using buttons

    var drawerState = rememberDrawerState(initialValue= DrawerValue.Closed)
    val scope = rememberCoroutineScope() //Update drawer state, is it closed?
    ModalNavigationDrawer( //Screen that displays above our content
        drawerState=drawerState, //Current state of drawer
        // drawer content
        drawerContent = { //Content of menu
            ModalDrawerSheet {
                Text("Menu",
                    modifier = Modifier
                    .padding(16.dp))
                HorizontalDivider() // Line

                //Show content 1
                NavigationDrawerItem(label = { Text("Content 1") }, //TITLE OF BUTTON //fist item
                    selected = false //is selected?
                    , onClick = {
                        component="Content1"
                        scope.launch {
                            drawerState.apply {
                                close() // Close drawer or side menu
                            }
                        }
                    }
                )
                // Show content 2
                NavigationDrawerItem(label = { Text("Content 2") }, //TITLE OF BUTTON //fist item
                    selected = false //is selected?
                    , onClick = {
                        component="Content2"
                        scope.launch {
                            drawerState.apply {
                                close() // Close drawer or side menu
                            }
                        }
                    }
                )

                //Buttons
                NavigationDrawerItem(label = { Text("Buttons") }, //TITLE OF BUTTON //fist item
                    selected = false //is selected?
                    , onClick = {
                        component="Buttons"
                        scope.launch {
                            drawerState.apply {
                                close() // Close drawer or side menu
                            }
                        }
                    }
                )

                //Floating
                NavigationDrawerItem(label = { Text("Floating Buttons") }, //TITLE OF BUTTON //fist item
                    selected = false //is selected?
                    , onClick = {
                        component="Floating"
                        scope.launch {
                            drawerState.apply {
                                close() // Close drawer or side menu
                            }
                        }
                    }
                )

                // Chips
                NavigationDrawerItem(label = { Text("Chips") }, //TITLE OF BUTTON //fist item
                    selected = false //is selected?
                    , onClick = {
                        component="Chips"
                        scope.launch {
                            drawerState.apply {
                                close() // Close drawer or side menu
                            }
                        }
                    }
                )
            }

         }) {
        Column{
            when(component){
                "Content1" -> {
                    Content1()
                }
                "Content2" ->{
                    Content2()
                }
                "Buttons" ->{
                    Buttons()
                }
                "Floating" ->{
                    FloatingButtons()
                }
                "Chips" ->{
                    Chips()
                }

            }
            /*Text(text = component)
            Text(text="This is the Components")
            Button(onClick = { navController.navigate("menu")}) {
            }
            Content1()
            Content2()*/
        }

    }


}

@Preview(showBackground = true)
@Composable
fun Content1(){
    Text(text="Content 1")
}

@Preview(showBackground = true)
@Composable
fun Content2(){
    Text(text="Content 2")
}

//@Preview(showBackground = true)
@Composable
fun Buttons(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ){
        Button(onClick = {}){
            Text("Filled")
        }
        FilledTonalButton(onClick = {}){ //Button with lower color
            Text("Tonal")
        }
        OutlinedButton(onClick = {}){ // Only border
            Text("Outlined")
        }
        ElevatedButton(onClick = {}) { //Buton with shadow
            Text("Elevated")
        }
        TextButton(onClick = { }) { //Hyperlink
            Text("Text")
        }
    }
}
//@Preview(showBackground = true)
@Composable
fun FloatingButtons() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        FloatingActionButton(onClick = { }) {
            Icon(Icons.Filled.Add,"")
        }
        SmallFloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Add,"")
        }
        LargeFloatingActionButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Add,"")
        }
        ExtendedFloatingActionButton(
            onClick = { /*TODO*/ },
            icon = {Icon(Icons.Filled.Add,"")},
            text = {Text("Extended")}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Chips() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        AssistChip(
            onClick = { /*TODO*/ },
            label = { Text("Assist Chip") },
            leadingIcon={Icon(Icons.Filled.Add,"",
                modifier= Modifier.size(AssistChipDefaults.IconSize) // Stays resizeable according to chip
            )
            }
        )

        var selected by remember { mutableStateOf(false) } // It can change the interface, initial value is false
        FilterChip(
            selected = selected,
            onClick = { selected = !selected},
            label = { Text("Toggle") },
            leadingIcon= {
                if(selected){
                    Icon(Icons.Filled.Add,"")
                }else{
                    null
                }
            }
        )
    }
}