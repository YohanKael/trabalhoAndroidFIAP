package com.example.fiaptrabandroid.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fiaptrabandroid.model.Results
import com.example.fiaptrabandroid.model.Vagas
import com.example.fiaptrabandroid.service.RetrofitFactory
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun FiltroVagas(navController: NavController) {
    var jobList by remember { mutableStateOf(listOf<Vagas>()) }
    var appliedJobs by remember { mutableStateOf(setOf<String>()) }
    var showDialog by remember { mutableStateOf(false) }
    var seniorityFilter by remember { mutableStateOf("") }
    var regionFilter by remember { mutableStateOf("") }

    val callVagas = RetrofitFactory()
        .getJobService()
        .getAllJobs()

    callVagas.enqueue(object : Callback<Results> {
        override fun onResponse(p0: Call<Results?>, resultado: Response<Results?>) {
            jobList = resultado.body()?.jobs ?: listOf()
        }

        override fun onFailure(p0: Call<Results?>, p1: Throwable) {}
    })

    val filteredJobs = jobList.filter { job ->
        (seniorityFilter.isEmpty() || job.jobLevel.contains(seniorityFilter, ignoreCase = true)) &&
                (regionFilter.isEmpty() || job.jobGeo.contains(regionFilter, ignoreCase = true))
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .background(Color(0xFFEEE5FF))
            .padding(10.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 15.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color(0xFFD7CEF5))
                        .padding(20.dp)
                ) {
                    Text(
                        text = "Filtrar Vagas",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    OutlinedTextField(
                        value = seniorityFilter,
                        onValueChange = { seniorityFilter = it },
                        label = { Text("Senioridade") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                    OutlinedTextField(
                        value = regionFilter,
                        onValueChange = { regionFilter = it },
                        label = { Text("Região") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            LazyColumn {
                items(jobList) { job ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding( 10.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .background(Color(0xFFD7CEF5))
                                .padding(10.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 20.dp,10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = job.companyLogo,
                                    contentDescription = "",
                                    modifier = Modifier.size(64.dp)
                                )
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(text = "Empresa: ${job.companyName}")
                                    Text(text = "Cargo: ${job.jobTitle}")
                                    Text(text = "Senioridade: ${job.jobLevel}")
                                    Text(text = "Localidade: ${job.jobGeo}")
                                }
                            }
                            Button(
                                onClick = {
                                    appliedJobs = appliedJobs + job.Id
                                    showDialog = true
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .padding(horizontal = 20.dp),

                                shape = RoundedCornerShape(8.dp),

                                ) {
                                Text(text = "Aplicar")
                            }
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            text = { Text(text = "Aplicado com sucesso!") },
            confirmButton = {
                Button(onClick = { showDialog = false }) {
                    Text(text = "OK")
                }
            },
            containerColor = Color.White
        )
    }
}

