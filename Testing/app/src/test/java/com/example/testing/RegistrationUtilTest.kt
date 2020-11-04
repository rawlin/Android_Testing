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

    @Test
    fun `empty password return false`(){
        val result=RegistrationUtil.validateRegistrationInput(
            "rawlin",
            "",
            ""
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `incorrectly repeated password`(){
        val result=RegistrationUtil.validateRegistrationInput(
            "rawlin",
            "asda",
            "backa"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `weak password returns false`(){
        val result=RegistrationUtil.validateRegistrationInput(
            "rawlin",
            "1",
            "1"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `uername exists return false`(){
        val result=RegistrationUtil.validateRegistrationInput(
            "rawlin1",
            "pass",
            "pass"
        )
        assertThat(result).isFalse()
    }


}