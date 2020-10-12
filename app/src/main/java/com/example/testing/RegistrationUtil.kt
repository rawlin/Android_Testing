package com.example.testing

object RegistrationUtil {

    /**
     * the input is not valid if...
     * ...the user/password is empty
     * ...the username is taken
     * ...the confirmed password !=real password
     * ...password contains less than 2 digits
     * */
    fun validateRegistrationInput(
        username:String,
        password:String,
        confirmedPassword:String
    ):Boolean{
        if(username.isEmpty()||password.isEmpty()){
            return false
        }
        if (password !=confirmedPassword){
            return false
        }
        if (password.count { it.isDigit() }<2){
            return false
        }
        return true
    }
}