@file:OptIn(ExperimentalMaterial3Api::class)

package com.github.klee0kai.cloud

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Box(
            modifier = Modifier.fillMaxSize()
                .background(color = Color.Gray.copy(alpha = 0.1f)),
        ) {


            Column(
                modifier = Modifier
                    .safeContentPadding()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(onClick = { showContent = !showContent }) {
                    Text("Click me!")
                }
                AnimatedVisibility(showContent) {
                    val greeting = remember { Greeting().greet() }
                    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                        Text("Compose: $greeting")
                    }
                }
            }

            TopAppBar(
                modifier = Modifier,
                title = {

                },
                navigationIcon = {

                },
                actions = {

                }
            )



            NavigationRail(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .background(color = Color.Gray.copy(alpha = 0.1f)),
            ) {

                NavigationRailItem(
                    modifier = Modifier,
                    icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "") },
                    label = { Text("Favorites") },
                    selected = false,
                    onClick = {},
                )

            }
        }
    }
}