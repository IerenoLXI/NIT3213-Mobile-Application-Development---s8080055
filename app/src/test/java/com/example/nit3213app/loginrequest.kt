package com.example.nit3213app

import com.example.nit3213app.data.model.LoginRequest
import org.junit.Test
import org.junit.Assert.*

class LoginRequestTest {

    @Test
    fun `login request fields should be set correctly`() {
        val request = LoginRequest("Aakrosh", "s8080055")
        assertEquals("Aakrosh", request.username)
        assertEquals("s8080055", request.password)
    }
}
