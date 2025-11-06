package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val nav = rememberNavController()
            NavHost(
                navController = nav,
                startDestination = "home",
            ) {
                composable("home") {
                    HomeScreen(
                        onMembersClicked = { nav.navigate("members_flow") },
                    )
                }
                //Nav graph for "members" feature
                navigation(startDestination = "members", route = "members_flow"){
                    composable("members") { backStackEntry ->
                        val parentEntry = remember(backStackEntry) {
                            nav.getBackStackEntry("members_flow")
                        }
                        val vm: MemberViewModel = viewModel(parentEntry)
                        MembersScreen(
                            { nav.navigate("addMember") },
                            { nav.popBackStack() },
                            vm = vm
                        )
                    }

                    composable("addMember") { backStackEntry ->
                        val parentEntry = remember(backStackEntry){
                            nav.getBackStackEntry("members_flow")
                        }
                        val vm: MemberViewModel = viewModel(parentEntry)
                        AddMemberScreen(
                            { nav.popBackStack() },
                            vm = vm
                        )
                    }
                }

            }
        }
    }
}