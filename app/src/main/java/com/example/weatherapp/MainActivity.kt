package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.retrofit.WeatherModel
import com.example.weatherapp.domain.WeatherViewModel
import com.example.weatherapp.presentation.WeatherPage
import com.example.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val weatherViewModel= ViewModelProvider(this)[WeatherViewModel::class.java]
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                WeatherPage(weatherViewModel)
            }
        }
    }
}
