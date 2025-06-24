package com.example.login.models
import androidx.lifecycle.ViewModel


class LoginViewModel: ViewModel() {
    var isLoggedIn: Boolean = false
    private set

    fun login(usurname: String, password: String) {
        if(usurname == "admin" && password == "password") {
            isLoggedIn = true
        } else {
            isLoggedIn = false

        }
    }
}