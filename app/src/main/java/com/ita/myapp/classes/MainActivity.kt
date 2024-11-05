package com.ita.myapp.classes

import android.graphics.fonts.FontStyle
import android.os.Build
import android.os.Bundle
import android.view.Menu
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import com.ita.myapp.classes.ui.theme.Myapp2Theme
import kotlin.math.max

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.ita.myapp.classes.ui.background.CustomWorker
import com.ita.myapp.classes.ui.biometrics.BiometricsScreen
import com.ita.myapp.classes.ui.contacts.ContactScreen
import com.ita.myapp.classes.ui.location.viewModel.SearchViewModel
import com.ita.myapp.classes.ui.location.views.HomeView
import com.ita.myapp.classes.ui.location.views.MapsSearchView
import com.ita.myapp.classes.ui.screens.HomeScreen
import com.ita.myapp.classes.ui.screens.HomeScreen

import com.ita.myapp.classes.ui.screens.LoginScreen

import com.ita.myapp.classes.ui.screens.Components

import com.ita.myapp.classes.ui.screens.MenuScreen
import dagger.hilt.android.AndroidEntryPoint
import java.time.Duration
import java.util.concurrent.TimeUnit

//import androidx.navigation.compose.NavHostController

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Colors also baterry and stuff bar

        //WorkManager
        //------------------------------------------
        val workRequest = OneTimeWorkRequestBuilder<CustomWorker>()
            .setInitialDelay(Duration.ofSeconds(10))
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.LINEAR,
                duration = Duration.ofSeconds(15)
            )
            .build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
        //By adding this, message "Hello from worker!" should be seen from LogCat

        //--------------------------------------------
        //-----------------------------------------
        //Maps
        //Instancia del ViewModel
        val viewModel: SearchViewModel by viewModels()

        //--------------------------------------------
        setContent { //Lo que se imprime en pantalla
            ComposeMultiScreenApp(searchVM = viewModel,this)
            /*Column(
                modifier= Modifier
                    .fillMaxSize() //De esa columna ocupa todo el espacio
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center, //Donde lo quieres alinear de forma vertical
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally //Centrarlo horizontalmente
            ){
                CustomText()
                Picture()
                Content1()
                Content2()
                //Text(text = "Simple Text")
                //ModifierExample()
                //ModifierExample2()
                //ModifierExample3()
            }


            //layouts
            /*Column{ //The main component // Solo se pone una vez
                Text(text="First Row")
                Text(text="Second Row")
                Text(text="Third Row")
                Row{
                    Text(text="TEXT 1")
                    Text(text = "TEXT1")
                    Text(text = "TEXT1")
                    Text(text = "TEXT1")
                    Text(text = "TEXT1")
                    Text(text = "TEXT1")

                }
                Box{ //Encima los elementos
                    Text(text = "Label 1")
                    Text(text = "Label 2")
                }
                Greeting(name = "World")
            }
//            Myapp2Theme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//
    }*/
            }*/
        }
    }
}
/*
//Cada Composable es un elemento visible
@Composable //Fragmento o componente de diseño
fun Greeting(name: String, modifier: Modifier = Modifier) {
    //Modifier te permite acceder a ciertas propiedades de los elementos
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true) //Si este se modifica, se muestra en tiempo real
@Composable
fun GreetingPreview() {
    Myapp2Theme {
        Greeting("Tony")
    }
}

@Preview(showBackground = true)
@Composable
fun ModifierExample(){
    Column(
        modifier=Modifier
            .padding(24.dp) //dp es la medida en android


    ){
        Text(text="Hello World")

    }
}


@Preview(showBackground = true)
@Composable
fun ModifierExample2(){
    Column(
        modifier= Modifier
            .padding(24.dp) //dp es la medida en android
            .fillMaxWidth()
            .clickable(onClick = { clickAction() }) //Evento de click
    ){
        Text(text="Hello World")

    }
}

fun clickAction(){
    println("Column Clicked")
}

@Composable
fun ModifierExample3(){
    Column(
        modifier = Modifier
            .fillMaxHeight() //Ocupa todo el ancho
            .padding(16.dp)
            .background(Color.Cyan) // Color de la columna
            .border(width = 2.dp, color = Color.Green)
            .width(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally, //Propias de Android Compose
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Text(text = "Item 1")
        Text(text = "Item 2")
        Text(text = "Item 3")
        Text(text = "Item 4")
        Text(text = "Item 5")
    }
}

@Composable
fun CustomText(){
    Column{
        Text(//String que está en /res/values/strings.xml
            stringResource(R.string.hello_world_text),
            //Color que está en /res/values/colors.xml
            color =  colorResource(R.color.purple_700),
            fontSize = 28.sp,
            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
            fontWeight = FontWeight.ExtraBold
        )
        val gradientColors = listOf(Color.Cyan,Color.Blue, Color.Red)
        Text(stringResource(R.string.hello_world_text),
            style = TextStyle(brush = Brush.linearGradient(colors = gradientColors))
        )
    }
}


@Composable
fun Picture(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
    ){

        //Agregar una imagen
        Image(
            modifier= Modifier
                .fillMaxWidth(),
            //Imagen importada en res/drawable/sushi.png
            painter = painterResource(R.drawable.sushi),
            contentDescription = "Sushi item",
            contentScale = ContentScale.Crop //Investigar scales
        )
    }
}


@Composable
fun Content1(){
    Card(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(5.dp)
    ){//New component

        Row{
            Image(
                painter = painterResource(id = R.drawable.sushi),
                contentDescription = "Sushi item",
                contentScale = ContentScale.Crop, //Check
                modifier=Modifier
                    .fillMaxHeight()
                    //.height(.dp)
            )
            Column{
                Text(
                    text = "This is a title",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(10.dp)
                )
                Text(stringResource(id = R.string.text_card),
                    textAlign = TextAlign.Justify,
                    lineHeight = 18.sp,
                    maxLines = 4, //Máximo de líneas
                    modifier =Modifier
                        .padding(10.dp))

            }

        }
    }
}

@Composable
fun Content2(){
    Card(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(5.dp)
    ){//New component
        Text(
            text = "This is a title",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(10.dp)
        )

            Image(
                painter = painterResource(id = R.drawable.sushi),
                contentDescription = "Sushi item",
                contentScale = ContentScale.Crop, //Check
                modifier=Modifier
                    .fillMaxWidth()
                //.height(.dp)
            )
            Text(stringResource(id = R.string.text_card),
                textAlign = TextAlign.Justify,
                lineHeight = 18.sp,
                modifier =Modifier
                    .padding(10.dp))

    }
}


@Preview(showBackground = true)
@Composable
fun BoxExample1(){
    Box( //Permite que se pongan fondos //Pone un objeto arriba de otro
        modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxWidth()
            .padding(5.dp)
    ){
        Image(painterResource(id = R.drawable.sushi),
            contentDescription = "Sushi item",
            contentScale = ContentScale.FillBounds //Llena el 100% del espacio
        )

        Row(
            modifier=Modifier
                //.fillMaxWidth()
                .fillMaxSize(),
                //.padding(0.dp, 150.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically

        )
        {

            Icon(imageVector = Icons.Filled.AccountBox, contentDescription = "Icon Account")
            Text(text="Text"
                /*textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp)*/)
        }


    }
}

@Preview(showBackground = true)
@Composable
fun BoxExample2(){
    Box(
        modifier=Modifier
            .background(Color.Magenta)
            .padding(5.dp)
            .size(250.dp)
    ){
        Text(text = "TopStart", modifier=Modifier.align((Alignment.TopStart)))
        Text(text = "TopCenter", modifier=Modifier.align((Alignment.TopCenter)))
        Text(text = "TopEnd", modifier=Modifier.align((Alignment.TopEnd)))

        Text(text = "CenterStart", modifier=Modifier.align((Alignment.CenterStart)))
        Text(text = "Center", modifier=Modifier.align((Alignment.Center)))
        Text(text = "CenterEnd", modifier=Modifier.align((Alignment.CenterEnd)))



        Text(text = "BottomStart", modifier=Modifier.align((Alignment.BottomStart)))
        Text(text = "BottomCenter", modifier=Modifier.align((Alignment.BottomCenter)))
        Text(text = "BottomEnd", modifier=Modifier.align((Alignment.BottomEnd)))

    }
}*/

@Composable
fun ComposeMultiScreenApp(searchVM: SearchViewModel, activity: AppCompatActivity){
    val navController = rememberNavController()
    Surface(color=Color.White){
        SetupNavGraph(navController=navController,searchVM,activity) //función propia //crea el grafo recordando el navcontroller donde nos encontramos
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController,searchVM: SearchViewModel,activity: AppCompatActivity){
    NavHost(navController = navController, startDestination = "biometrics"){ //índice de pantallas //Usa el nav controller de ahorita y empieza desde el índice definido
        composable("menu"){ MenuScreen(navController) } //Rutas
        composable("home"){ HomeScreen(navController) }
        composable("components"){ Components(navController)}
        composable("login"){ LoginScreen(navController = navController)}

        // Rutas de contactos

        composable("contacts"){ ContactScreen(navController = navController) }

        //Biometricos
        composable("biometrics"){ BiometricsScreen(navController = navController, activity = activity)}

        // Ruta para `MapsSearchView` que recibe latitud, longitud y dirección como argumentos
        composable("homeMaps"){ HomeView(navController = navController, searchVM = searchVM)}
        composable("MapsSearchView/{lat}/{long}/{address}", arguments = listOf(
            navArgument("lat") { type = NavType.FloatType },
            navArgument("long") { type = NavType.FloatType },
            navArgument("address") { type = NavType.StringType }
        )) {
            // Obtención de los argumentos con valores predeterminados en caso de que falten
            val lat = it.arguments?.getFloat("lat") ?: 0.0
            val long = it.arguments?.getFloat("long") ?: 0.0
            val address = it.arguments?.getString("address") ?: ""
            MapsSearchView(lat.toDouble(), long.toDouble(), address )
        }

    }

}