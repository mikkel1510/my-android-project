package com.example.myapp.chat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRequestScreen(onBackPress: () -> Unit, vm: ChatViewModel = viewModel()){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Request") },
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
                }
            )
        }
    ){ innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ){
            Text("Request Message")
            var requestMessage by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = requestMessage,
                onValueChange = { requestMessage = it },
                label = { Text("Request Title") }
            )

            Text("Request Amount")
            var amount by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Request Amount") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            )
            Button(onClick = { vm.addRequestMessage("1", requestMessage, amount.toDouble()); onBackPress() }) { //TODO: SenderID should use auth
                Text("Create")
            }


        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateRequestPreview(){
    CreateRequestScreen({})
}