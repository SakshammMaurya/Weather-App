package com.example.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.data.retrofit.NetworkResponse
import com.example.weatherapp.data.retrofit.WeatherModel
import com.example.weatherapp.domain.WeatherViewModel

@Composable
fun WeatherPage(viewModel: WeatherViewModel) {

    var city by remember { mutableStateOf("") }

    val weatherResult = viewModel.weatherResult.observeAsState()

    val location = remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )

    {
        Image(
            painter = painterResource(id = R.drawable.search),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            // contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            when (val result = weatherResult.value) {
                is NetworkResponse.Error -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.fail),
                            contentDescription = null,
                            alignment = Alignment.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = result.message,
                            fontSize = 20.sp
                        )
                    }
                }

                NetworkResponse.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxWidth().fillMaxWidth().padding(top=150.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is NetworkResponse.Success -> {
                    WeatherDetails2(data = result.data)
                }

                null -> {}
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedTextColor = Color.LightGray,
                    unfocusedBorderColor = Color.LightGray,
                    unfocusedLabelColor = Color.LightGray,
                    focusedBorderColor = Color.LightGray,
                    focusedTextColor = Color.LightGray
                    // unfocusedLeadingIconColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                value = city,
                modifier = Modifier.weight(1f),
                onValueChange = {
                    city = it
                },
                label = {
                    Text(
                        text = "search for any location",
                        color = Color.LightGray,
                        fontSize = 16.sp
                    )
                }
            )
            IconButton(onClick = {
                viewModel.getData(city)
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = Color.LightGray,
                    contentDescription = "",
                    modifier = Modifier.size(40.dp)

                )
            }


        }
    }

}

//@Composable
//fun WeatherDetails(data: WeatherModel) {
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        //   verticalArrangement = Arrangement.Center
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.Start,
//            verticalAlignment = Alignment.Bottom
//        ) {
//            Icon(
//                imageVector = Icons.Default.LocationOn,
//                contentDescription = " Location icon",
//                modifier = Modifier.size(40.dp)
//            )
//            Text(text = data.location.name, fontSize = 30.sp)
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(text = data.location.country, fontSize = 18.sp, color = Color.Gray)
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//        Text(
//            text = "${data.current.temp_c}+ °C",
//            fontSize = 56.sp,
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center
//        )
//        AsyncImage(
//            modifier = Modifier.size(160.dp),
//            model = "https:${data.current.condition.icon}".replace("64x64", "128x128"),
//            contentDescription = " Condition Icon"
//        )
//        Text(
//            text = data.current.condition.text,
//            fontSize = 20.sp,
//            color = Color.Gray,
//            textAlign = TextAlign.Center
//        )
//    }
//
//}
