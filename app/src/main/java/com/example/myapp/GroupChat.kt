package com.example.myapp

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupChatScreen(onInfoPress: () -> Unit, onBackPress: () -> Unit){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("\'Group name\'") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { onBackPress() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onInfoPress() }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Add new member",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ){ innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ChatBox(modifier = Modifier.weight(1f))
            BottomToolBar()
        }

    }
}

@Composable
fun ChatBox(modifier: Modifier){
    LazyColumn(
        modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray)
            .padding(15.dp)
    ) {
        items(20){
            ChatRow(true ){
                ChatBubble("hello")
            }
            ChatRow(false) {
                ChatMessage("Sup")
            }
            ChatRow(false){
                ChatBubble("yamudda")
            }
            ChatRow(true) {
                ChatMessage("hello")
            }
        }
    }
}

@Composable
fun ChatRow(outgoing: Boolean, payload: @Composable () -> Unit){
    val modifier = Modifier
        .padding(bottom = 5.dp)
        .fillMaxSize()
    val alignment = if (outgoing) Alignment.Start else Alignment.End

    Column(
        modifier,
        horizontalAlignment = alignment
    ) {
        Row {
            Text(if (outgoing) "from you" else "from me")
        }
        payload()
    }
}

@Composable
fun ChatBubble(message: String){
    Row(Modifier
        .width(200.dp)
        .height(120.dp)
        .clip(
            RoundedCornerShape(
                30.dp
            )
        )
    ) {
        Box(Modifier
            .fillMaxSize()
            .background(Color.Blue),
            contentAlignment = Alignment.Center
        ){
            Text(message, color = Color.White)
        }
    }
}

@Composable
fun ChatMessage(message: String){
    Row(Modifier
        .clip(
            RoundedCornerShape(16.dp)
        )
        .background(Color.White)
        .widthIn(min = 150.dp)
        .padding(10.dp)
    ) {
        Text(message)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomToolBar(){
    Column(Modifier
        .fillMaxWidth()
        .height(130.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Color.LightGray)
        .padding(5.dp)
    ){
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            var messageText by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                value = messageText,
                onValueChange = { messageText = it },
                label = { Text("Message") },
                colors = TextFieldDefaults.colors(focusedContainerColor = Color.White),
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {}) {
                IconButton(onClick = {}, modifier = Modifier.size(30.dp)) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Add new member",
                        tint = Color.White
                    )
                }
            }
        }
        Row(Modifier
            .fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ){
            Button(onClick = {}, Modifier.width(150.dp),){
                Text("Create request")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GroupChatScreenPreview(){
    GroupChatScreen({}, {})
}