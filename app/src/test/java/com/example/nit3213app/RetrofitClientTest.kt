package com.example.nit3213app

import com.example.nit3213app.di.NetworkModule
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Retrofit

class RetrofitClientTest {

    @Test
    fun `getInstance should return a non-null Retrofit object`() {
        val instance: Retrofit = NetworkModule.provideRetrofit(
            NetworkModule.provideOkHttpClient(),
            NetworkModule.provideGson()
        )
        assertNotNull(instance)
    }

    @Test
    fun `Retrofit base URL should be correct`() {
        val instance: Retrofit = NetworkModule.provideRetrofit(
            NetworkModule.provideOkHttpClient(),
            NetworkModule.provideGson()
        )
        val baseUrl = instance.baseUrl().toString()
        assertEquals("https://nit3213api.onrender.com/", baseUrl)
    }
}
