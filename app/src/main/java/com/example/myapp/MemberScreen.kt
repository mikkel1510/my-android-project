package com.example.myapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapp.ui.theme.MyAppTheme

@Composable
fun PersonList(members: List<MemberViewModel.Member>) {
    Column {
        for(member in members){
            PersonBar(member)
        }
    }
}

@Composable
fun PersonBar(member: MemberViewModel.Member){
    val vm: MemberViewModel = viewModel()
    val dialogOpen = remember { mutableStateOf(false) }

    RemoveMemberDialog(      //TODO: Should be on the screen instead of every individual person
        active = dialogOpen.value,
        onDismissRequest = { dialogOpen.value = false },
        onConfirmation = { vm.removePerson(member.id) },
        memberName = member.name
    )
    Row(modifier = Modifier
        .padding(10.dp)
        .border(width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
        .padding(10.dp)
        .width(250.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.user_icon),
            contentDescription = "User icon"
        )
        Text(
            member.name
        )
        Button(
            onClick = { dialogOpen.value = true }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            modifier = Modifier
                .width(100.dp)){
            Text("Remove")
        }
    }
}



@Composable
fun RemoveMemberDialog(
    active: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    memberName: String
){
    if (!active) return
    AlertDialog(
        title = {
            Text("Confirm removal")
        },
        text = {
            Row {
                Text("Remove ")
                Text(memberName, fontWeight = FontWeight.Bold)
                Text("?")
            }

        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            Button(onClick = { onConfirmation() }) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button({ onDismissRequest() }) {
                Text("No")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class) //TODO: Maybe choose another version
@Composable
fun MembersScreen(onAddMembers: () -> Unit, onBackPressed: () -> Unit, vm: MemberViewModel = viewModel()){
    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text("Members") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { onBackPressed() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onAddMembers() }) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add new member",
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PersonList(vm.members)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MemberScreenPreview() {
    val vm: MemberViewModel = viewModel()
    vm.addPerson("Bob")
    vm.addPerson("Pete")
    vm.addPerson("Steve")

    MyAppTheme {
        MembersScreen(onAddMembers = {}, onBackPressed = {})

        //Set 'active' to true for preview
        RemoveMemberDialog(false, {}, {}, "Bob")
    }

}