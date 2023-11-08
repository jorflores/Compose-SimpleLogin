package com.example.simplelogin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simplelogin.data.LoginUserRequest
import com.example.simplelogin.data.LoginUserResponse
import com.example.simplelogin.data.RegisterUserRequest
import com.example.simplelogin.data.RegisterUserResponse
import com.example.simplelogin.service.UserService
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UserViewModel(private val userService: UserService): ViewModel() {

    private val _login = MutableLiveData<LoginUserResponse>()
    val login: LiveData<LoginUserResponse?> = _login


    private val _register = MutableLiveData<RegisterUserResponse>()
    val register: LiveData<RegisterUserResponse?> = _register


    // Registro de usuario
    fun registerUser(user: RegisterUserRequest) {

        viewModelScope.launch {
            try {
                val response = userService.registerUser(user)
                _register.value = response
            } catch (e: Exception) {
                Log.e("ERROR-API", "An error has ocurred: ${e.message}")
            }
        }
    }

    // Login de usuario
    fun loginUser(user: LoginUserRequest) {

        // Reset the login result to nul before making a new login request
        _login.value = null

        viewModelScope.launch {
            try {
                val response = userService.loginUser(user)
                _login.value = response
            }
            catch (e: HttpException){

                when(e.code()){

                    401 -> {
                        val errorMessage = "Credenciales Incorrectas"
                        val response = LoginUserResponse(false,errorMessage,"")
                        _login.value = response
                    }
                    else ->{
                        val errorMessage = e.message()
                        val response = LoginUserResponse(false,errorMessage,"")
                        _login.value = response
                    }

                }

                Log.e("ERROR-API", "A Http exception error has ocurred: ${e.message}")

            }


            catch (e: Exception) {
                Log.e("ERROR-API", "An error has ocurred: ${e.message}")
            }
        }
    }


}