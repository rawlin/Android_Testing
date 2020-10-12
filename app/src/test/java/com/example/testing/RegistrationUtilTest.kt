package com.example.testing


import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest{

    @Test
    fun `empty username return false`(){
        val result=RegistrationUtil.validateRegistrationInput(
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `valid username and correct repeated passsword returns true`(){
        val result=RegistrationUtil.validateRegistrationInput(
            "rawlin",
            "123",
            "123"
        )
        assertThat(result).isTrue()
    }
}