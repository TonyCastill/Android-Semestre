package com.ita.myapp.classes.ui.network

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter


@Composable
fun NetworkIMage(isHighQuality:Boolean){
    val imageUrl = if(isHighQuality){
        //VER EN TEAMS
        //Alta calidad
        "https://st4.epo"
    }else{
        //Baja calidad
        ""
    }

    // CHECAR
    /*Image(
        painter = rememberImagePainter(data = imageUrl),
        contentDecription = "Imagen de Red",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )*/
}

@Composable
fun ConnectionCard(title: String, content:String, networkSpeed: Int? = null){
    Card(
      modifier = Modifier
          .fillMaxSize(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){

        //TODO

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            //Título de la tarjeta o estado de conexión
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
            // Contenido pricnopal de la tarjeta
            Text(
                text = content,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            //Si la velodicda de la red es proporcionada, la mostramos
            networkSpeed?.let {
                Text(
                    text ="Velocidad de Red: $it kbps",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Center
                )
            }

        }

    }

}