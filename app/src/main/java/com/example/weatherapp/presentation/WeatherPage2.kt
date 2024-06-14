package com.example.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.R
import com.example.weatherapp.data.retrofit.WeatherModel
import com.example.weatherapp.domain.WeatherViewModel

@Composable
fun WeatherDetails2(data: WeatherModel) {
    val localTime = data.location.localtime.split(" ");
    val date = localTime[0];
    val time = localTime[1];
    val hardTime = localTime[1].split(":")[0]

    // outer box
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(
                id = (
                        (
                                if (hardTime.toFloat() >= 0f && hardTime.toFloat() < 7f) R.drawable.sunset
                                else if (hardTime.toFloat() >= 7f && hardTime.toFloat() < 18f) R.drawable.morning
                                else if (hardTime.toFloat() >= 18f && hardTime.toFloat() < 20f) R.drawable.sunset
                                else if (hardTime.toFloat() >= 20f && hardTime.toFloat() < 25f) R.drawable.night
                                else R.drawable.night
                                ) as Int
                        )
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center
//        ) {
        // top info
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp, start = 16.dp, end = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                // Spacer(modifier = Modifier.height(80.dp))
                Text(
                    text = "${data.current.temp_c} °C",
                    fontSize = 42.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    //modifier = Modifier.padding(start = 16.dp, bottom = 2.dp)
                )
                // weather condition
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier.size(50.dp),
                        model = "https:${data.current.condition.icon}".replace(
                            "64x64",
                            "128x128"
                        ),
                        contentDescription = "Condition Icon"
                    )
                    //Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = data.current.condition.text,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.height(60.dp))

                // location
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(35.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(
                            text = data.location.name,
                            fontSize = 22.sp,
                            color = Color.White,
                        )
                        Text(
                            text = data.location.country,
                            fontSize = 16.sp,
                            color = Color.White,
                        )
                    }

                }
            }
        }

        //bottom info
        Box(
            modifier = Modifier
                //.padding(top=500.dp, start = 16.dp,end=16.dp)
                .fillMaxWidth()
//                    .height(400.dp)
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            //contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherKeyValue("Humidity", data.current.humidity)
                    WeatherKeyValue("Wind Speed", data.current.wind_kph)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherKeyValue("UV", data.current.uv)
                    WeatherKeyValue("Precipitation", data.current.precip_mm)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherKeyValue("Date", date)
                    WeatherKeyValue("Time", time)
                }

            }

        }
        // }


    }


}

@Composable
fun WeatherKeyValue(key: String, value: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .width(160.dp)
            .height(80.dp)
            ,
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = value.toString(),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Text(
                text = key.toString(),
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }


}