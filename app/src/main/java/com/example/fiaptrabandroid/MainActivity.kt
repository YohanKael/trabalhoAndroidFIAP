package com.example.fiaptrabandroid

import android.os.Bundle
import androidx.navigation.compose.composable

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.fiaptrabandroid.screens.LoginScreen
import com.example.fiaptrabandroid.ui.theme.FiapTrabAndroidTheme
import com.example.fiaptrabandroid.screens.FeedbackScreen
import com.example.fiaptrabandroid.screens.FiltroVagas
import com.example.fiaptrabandroid.screens.JobListScreen
import com.example.fiaptrabandroid.screens.TelaMissao


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FiapTrabAndroidTheme {
                setContent {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = "login"
                        ) {
                            composable(route = "login") { LoginScreen(navController) }
                            composable(route = "feedback") { FeedbackScreen(navController) }
                            composable(route = "jobList") { JobListScreen(navController) }
                            composable(route = "Missao") { TelaMissao(navController) }
                            composable(route = "FiltroVagas") { FiltroVagas(navController) }

                        }
                        }

                    }
                }

            }
        }
    }





