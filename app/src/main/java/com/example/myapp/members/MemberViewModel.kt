package com.example.myapp.members

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.myapp.auth.User

class MemberViewModel : ViewModel() {

    private val _members = mutableStateListOf<User>()
    val members: List<User> get() = _members;

    fun addMember(user: User){
        _members.add(user)
    }

    fun addMembers(users: List<User>){
        _members.addAll(users)
    }

    fun removeMember(user: User) {
        _members.remove(user)
    }

}