package com.example.fiaptrabandroid.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fiaptrabandroid.R

@SuppressLint("SuspiciousIndentation")
@Composable
fun FeedbackScreen(navController: NavController) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var feedbackText by remember { mutableStateOf(TextFieldValue("")) }
    var feedbackList by remember { mutableStateOf(listOf<Triple<String, String, Int>>()) }
    var selectedStars by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFEEE5FF))
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.feedback_sobre_vagas),
                fontSize = 22.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Seu Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = feedbackText,
                onValueChange = { feedbackText = it },
                label = { Text("Digite seu feedback") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                (1..5).forEach { index ->
                    IconButton(onClick = { selectedStars = index }) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Estrela $index",
                            tint = if (index <= selectedStars) Color(0xFFFFD700) else Color.Gray
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (name.text.isNotBlank() && feedbackText.text.isNotBlank() && selectedStars > 0) {
                        feedbackList =
                            feedbackList + Triple(name.text, feedbackText.text, selectedStars)
                        name = TextFieldValue("")
                        feedbackText = TextFieldValue("")
                        selectedStars = 0
                        showDialog = true
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = stringResource(R.string.enviar), color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.feedbacks_recentes),
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(scrollState)
            ) {
                feedbackList.forEach { (userName, feedback, stars) ->
                    FeedbackCard(userName, feedback, stars)
                }
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        Button(
                            onClick = { showDialog = false }
                        ) {
                            Text("OK")
                        }
                    },
                    title = { Text("Feedback Enviado") },
                    text = { Text("Seu feedback foi enviado, obrigado!") }
                )
            }
        }
    }

    @Composable
    fun FeedbackCard(userName: String, feedback: String, stars: Int) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp),

        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD7CEF5))
                .padding(12.dp)
            ) {
                Text(text = userName, fontSize = 16.sp, color = Color.DarkGray)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    repeat(5) { index ->
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Estrela ${index + 1}",
                            tint = if (index < stars) Color(0xFFFFD700) else Color.Gray
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = feedback, fontSize = 16.sp, color = Color.Black)
            }
        }
    }
