package com.example.fiaptrabandroid.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fiaptrabandroid.R


@Composable
fun TelaMissao(
    navController: NavController?
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()

                    .wrapContentHeight(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFEEE5FF))

                        .padding(10.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = "Logo Oportuno",
                        modifier = Modifier
                            .width(150.dp)
                            .padding(top = 10.dp)
                    )

                    Text(
                        text = stringResource(R.string.titulo_missao),
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        color = Color(0xFFA56AFC),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.conteudo_missao),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 17.sp),
                            textAlign = TextAlign.Unspecified,
                            softWrap = true,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFFA56AFC),
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Image(
                        painter = painterResource(R.drawable.missao),
                        contentDescription = "Imagem Missão",
                        modifier = Modifier
                            .width(350.dp)
                            .padding(top = 10.dp, bottom = 20.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        contentScale = ContentScale.Crop

                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Button(
                            onClick = {
                                navController!!.navigate("jobList")
                            },
                        ) {
                            Text(
                                text = stringResource(R.string.button_vagas),
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Button(
                            onClick = {
                                navController!!.navigate("feedback")
                            },
                        ) {
                            Text(
                                text = stringResource(R.string.button_feedback),
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
            }
        }
    }
}