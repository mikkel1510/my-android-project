package com.example.gimmedamoney

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.graphics.Color;
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gimmedamoney.ui.theme.GimmeDaMoneyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GimmeDaMoneyTheme {
                    MembersScreen()
                }
            }
        }
    }
data class Member(
    val name: String
)


@Composable
fun PersonList(members: MutableList<Member>) {
    Column {
        for(member in members){
            Person(member.name)
        }
    }
}


@Composable
fun Person(name: String){
    Row(modifier = Modifier
        .padding(10.dp)
        .border(width = 2.dp, color = Color.LightGray, shape = RoundedCornerShape(8.dp))
        .padding(10.dp)
        .width(150.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Image(
            modifier = Modifier.size(25.dp),
            painter = painterResource(id = R.drawable.user_icon),
            contentDescription = "User icon"
        )
        Text(
            name
        )
    }
}

@Composable
fun AddMemberBar(members: MutableList<Member>, modifier: Modifier = Modifier){
    var memberName by rememberSaveable() { mutableStateOf("") }

    Row (modifier
        .fillMaxWidth()
        .padding(12.dp)
        .height(75.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ){
        OutlinedTextField(
            value = memberName,
            onValueChange = { memberName = it },
            label = { Text("Name") },
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 56.dp)
        )
        Button(
            onClick = { members.add(Member(memberName)); memberName = "" },
            modifier = Modifier
                .width(75.dp)
        ) {
            Text("Add")
        }
    }
}


@Composable
fun MembersScreen(){
    val members = rememberSaveable { mutableStateListOf<Member>() }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PersonList(members)
        AddMemberBar(members, Modifier.fillMaxWidth())
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GimmeDaMoneyTheme {
        MembersScreen()
    }
}