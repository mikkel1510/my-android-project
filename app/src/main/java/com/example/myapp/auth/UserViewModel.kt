package com.example.myapp.auth

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.auth.RetrofitClient
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val retrofitClient = RetrofitClient()

    init {
        fetchUsers()
    }

    private val _users = mutableStateListOf<User>()
    val users: List<User> get() = _users

    fun addUser(id: Int, name: String, email: String){
        _users.add(User(id, name, email))
    }

    fun fetchUsers(){
        viewModelScope.launch {
            val response = retrofitClient.api.getUsers()
            val mapped = response.map { User(it.id, it.name, it.email) }
            _users.clear()
            _users.addAll(mapped)
        }
    }
}